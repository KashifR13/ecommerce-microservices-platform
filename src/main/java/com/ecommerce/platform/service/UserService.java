package com.ecommerce.platform.service;

import com.ecommerce.platform.model.User;
import com.ecommerce.platform.repository.UserRepository;
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
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(User user) {
        // Business logic for user login
        return "User logged in successfully";
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}