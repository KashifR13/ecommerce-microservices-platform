package com.ecommerce.platform.service;

import com.ecommerce.platform.model.User;
import com.ecommerce.platform.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "User already exists";
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null || !BCrypt.checkpw(user.getPassword(), existingUser.getPassword())) {
            return "Invalid username or password";
        }
        return "User logged in successfully";
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public String changePassword(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (isUserNotFound(existingUser)) {
            return "User not found";
        }
        if (isPasswordEmpty(user.getPassword())) {
            return "Password cannot be empty";
        }
        if (isSameAsOldPassword(user.getPassword(), existingUser.getPassword())) {
            return "New password cannot be the same as the old password";
        }
        existingUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(existingUser);
        return "Password changed successfully";
    }

    private boolean isUserNotFound(User user) {
        return user == null;
    }

    private boolean isPasswordEmpty(String password) {
        return password == null || password.isEmpty();
    }

    private boolean isSameAsOldPassword(String newPassword, String oldPassword) {
        return BCrypt.checkpw(newPassword, oldPassword);
    }

}