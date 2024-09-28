package com.claims.claims.services.impl;

import com.claims.claims.models.UserEntity;
import com.claims.claims.repository.UserRepository;
import com.claims.claims.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // Constructor-based Dependency Injection
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users who are not marked as deleted.
     *
     * @return List of UserEntity
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Updates the enabled status of a user.
     *
     * @param userId  ID of the user to update
     * @param enabled New enabled status
     */
    @Override
    @Transactional
    public void updateUserStatus(Long userId, boolean enabled) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    /**
     * Soft deletes a user by marking them as deleted.
     *
     * @param userId ID of the user to delete
     */
    @Override
    @Transactional
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        user.setIsDeleted(true); // Assuming 'deleted' field exists in BaseEntity
        userRepository.save(user);
    }

    /**
     * Retrieves users based on their role.
     *
     * @param role Role name to filter users
     * @return List of UserEntity with the specified role
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getUsersByRole(String role) {
        return userRepository.findByRoleDescription(role);
    }

    /**
     * Saves a new user to the repository.
     *
     * @param user UserEntity to save
     */
    @Override
    @Transactional
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    /**
     * Updates an existing user's information.
     *
     * @param userId      ID of the user to update
     * @param updatedUser UserEntity containing updated information
     */
    @Override
    @Transactional
    public void updateUser(Long userId, UserEntity updatedUser) {
        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setCountry(updatedUser.getCountry());
        existingUser.setBirthDate(updatedUser.getBirthDate());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setCity(updatedUser.getCity());
        existingUser.setPostal_code(updatedUser.getPostal_code());

        if (updatedUser.getRole() != null) {
            existingUser.setRole(updatedUser.getRole());
        }

        // Handle password update if necessary
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(updatedUser.getPassword()); // Ensure password is encoded
        }

        userRepository.save(existingUser);
    }

    /**
     * Retrieves the profile of the currently authenticated user.
     *
     * @param userId ID of the user whose profile is to be retrieved
     * @return UserEntity representing the user's profile
     */
    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserProfile(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();

        UserEntity currentUser = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found"));

        if (!currentUser.getId().equals(userId)) {
            throw new SecurityException("Access denied: Cannot access other user's profile");
        }

        return currentUser;
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email Email of the user to retrieve
     * @return UserEntity matching the given email
     */
    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }
}
