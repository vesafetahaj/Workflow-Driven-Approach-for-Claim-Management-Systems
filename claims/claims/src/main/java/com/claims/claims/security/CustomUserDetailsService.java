package com.claims.claims.security;

import com.claims.claims.models.UserEntity;
import com.claims.claims.models.principal.UserPrincipal;
import com.claims.claims.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).get();
        if (user ==null){
            throw new UsernameNotFoundException("This user does not exists");
        }
        UserPrincipal userPrincipal=new UserPrincipal(user);
        return userPrincipal;
    }

}