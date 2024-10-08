package com.claims.claims.repository;

import com.claims.claims.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String username);
    Boolean existsByEmail(String username);
    List<UserEntity> findAll();
    List<UserEntity> findByRoleDescription(String role);
}
