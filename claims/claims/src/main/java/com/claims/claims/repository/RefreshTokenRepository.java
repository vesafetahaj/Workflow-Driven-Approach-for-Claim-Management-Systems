package com.claims.claims.repository;

import com.claims.claims.models.RefreshToken;
import com.claims.claims.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    RefreshToken findByToken(String token);
    RefreshToken findByUserInfo(UserEntity user);
}