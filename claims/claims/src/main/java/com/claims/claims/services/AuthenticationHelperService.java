package com.claims.claims.services;


import com.claims.claims.models.UserEntity;

public interface AuthenticationHelperService {
    UserEntity getUserEntityFromAuthentication();
}