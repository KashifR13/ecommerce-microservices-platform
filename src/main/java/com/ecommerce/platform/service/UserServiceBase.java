package com.ecommerce.platform.service;

import com.ecommerce.platform.model.User;
import com.ecommerce.platform.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserServiceBase {

    protected final UserRepository userRepository;

    @Autowired
    public UserServiceBase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected String getValidationMessage(User user, User existingUser) {
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