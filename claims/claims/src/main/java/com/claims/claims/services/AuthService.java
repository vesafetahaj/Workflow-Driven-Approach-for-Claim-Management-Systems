package com.claims.claims.services;

import com.claims.claims.dto.JwtResponseDto;
import com.claims.claims.dto.LoginDto;
import com.claims.claims.dto.RefreshTokenRequestDto;
import com.claims.claims.dto.RegisterDto;

public interface AuthService {
    JwtResponseDto login(LoginDto loginDto);
    void register(RegisterDto registerDto);
    JwtResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}