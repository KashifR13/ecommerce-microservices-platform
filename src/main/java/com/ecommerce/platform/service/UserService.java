package com.ecommerce.platform.service;

import com.ecommerce.platform.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String register(User user) {
        // Business logic for user registration
        return "User registered successfully";
    }

    public String login(User user) {
        // Business logic for user login
        return "User logged in successfully";
    }

    public User getUserById(Long id) {
        // Business logic to get user by ID
        return new User(); // Replace with actual user retrieval logic
    }
}