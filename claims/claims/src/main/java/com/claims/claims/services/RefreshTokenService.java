package com.claims.claims.services;

import com.claims.claims.models.RefreshToken;

import java.time.Instant;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String username, String token, Instant expiryDate);
    RefreshToken findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token);
    String getRefreshTokenByUsername(String username);
}