package com.claims.claims.services.impl;

import com.claims.claims.models.UserEntity;
import com.claims.claims.services.AuthenticationHelperService;
import com.claims.claims.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationHelperServiceImpl implements AuthenticationHelperService {
    private final UserService userService;
    public AuthenticationHelperServiceImpl(UserService userService){
        this.userService=userService;
    }
    public UserEntity getUserEntityFromAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            return this.userService.getUserByEmail(authentication.getName());
        }
        throw new RuntimeException("Not authenticated user!");
    }
}