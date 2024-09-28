package com.claims.claims.services;

import com.claims.claims.models.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    void updateUserStatus(Long userId, boolean enabled);
    void deleteUser(Long userId);
    List<UserEntity> getUsersByRole(String role);
    void saveUser(UserEntity user);
    void updateUser(Long userId, UserEntity updatedUser);
    UserEntity getUserProfile(Long userId);
    UserEntity getUserByEmail(String email);
}