package com.claims.claims.services.impl;

import com.claims.claims.dto.*;
import com.claims.claims.models.*;
import com.claims.claims.repository.*;
import com.claims.claims.security.JWTGenerator;
import com.claims.claims.services.AuthService;
import com.claims.claims.services.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JWTGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final  PasswordEncoder passwordEncoder;
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, JWTGenerator jwtGenerator,
                           AuthenticationManager authenticationManager,
                           RefreshTokenService refreshTokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtGenerator = jwtGenerator;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JwtResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.isAuthenticated()) {
            String userEmail = loginDto.getEmail();

            UserEntity user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));
            Role role = user.getRole();
            boolean enabled = user.isEnabled();
            return jwtGenerator.generateTokens(user.getId(), userEmail, role.getDescription(), enabled);
        }
        throw new UsernameNotFoundException("Invalid user request");
    }

    @Override
    public void register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new IllegalArgumentException("Username is taken!");
        }

        UserEntity user = new UserEntity();
        user.setEmail(registerDto.getEmail());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setCountry(registerDto.getCountry());
        user.setCity(registerDto.getCity());
        user.setAddress(registerDto.getAddress());
        user.setPhone(registerDto.getPhone());
        user.setPostal_code(registerDto.getPostal_code());


        String hashedPassword = passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(hashedPassword);

        Role role = roleRepository.findByDescription("USER")
                .orElseThrow(() -> new IllegalStateException("Default role not found"));
        user.setRole(role);
        UserEntity savedUser = userRepository.save(user);
        System.out.println("User saved: " + savedUser);
    }

    @Override
    public JwtResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        RefreshToken refreshToken = this.refreshTokenService.findByToken(refreshTokenRequestDto.getRefreshToken());


        if (refreshToken == null || refreshToken.isExpired()) {
            return new JwtResponseDto(null, null);
        }

        UserEntity user = refreshToken.getUserInfo();
        String newAccessToken = jwtGenerator.generateAccessToken(user.getEmail(), user.getId(), user.getRole().getDescription(), user.isEnabled());
        return new JwtResponseDto(newAccessToken, null);
    }
}