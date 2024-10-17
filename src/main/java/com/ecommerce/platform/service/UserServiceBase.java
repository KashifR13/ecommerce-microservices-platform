package com.ecommerce.platform.service;

import com.ecommerce.platform.model.User;
import com.ecommerce.platform.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class UserServiceBase {

    protected final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceBase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    protected String getValidationMessageForRegistration(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "User already exists";
        }
        return null;
    }

    protected String getValidationMessageForUserDetailsModification(User user, User existingUser) {
        if (isUserNotFound(existingUser)) {
            return "User not found";
        }
        if (isPasswordEmpty(user.getPassword())) {
            return "Password cannot be empty";
        }
        if (isSameAsOldPassword(user.getPassword(), existingUser.getPassword())) {
            return "New password cannot be the same as the old password";
        }
        return null;
    }

    protected String getValidationMessageForPasswordChange(String currentPassword, String newPassword, User existingUser) {
        if (existingUser == null) {
            return "User not found";
        }
        if (!passwordEncoder.matches(currentPassword, existingUser.getPassword())) {
            return "Current password is incorrect";
        }
        if (currentPassword.equals(newPassword)) {
            return "New password cannot be the same as the current password";
        }
        return null;
    }

    protected boolean isUserNotFound(User user) {
        return user == null;
    }

    protected boolean isPasswordEmpty(String password) {
        return password == null || password.isEmpty();
    }

    protected boolean isSameAsOldPassword(String newPassword, String oldPassword) {
        return BCrypt.checkpw(newPassword, oldPassword);
    }

}