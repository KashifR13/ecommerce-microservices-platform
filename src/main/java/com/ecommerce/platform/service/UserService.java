package com.ecommerce.platform.service;

import com.ecommerce.platform.model.User;
import com.ecommerce.platform.repository.UserRepository;
import com.ecommerce.platform.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends UserServiceBase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        super(userRepository, passwordEncoder);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(User user) {
        String validationMessage = getValidationMessageForRegistration(user);
        if (validationMessage != null) return validationMessage;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return jwtUtil.generateToken(existingUser.getUsername());
        }
        return "Invalid username or password";
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public String modifyUserDetails(User user, Long userId) {
        User existingUser = userRepository.findById(userId).orElse(null);
        String validationMessage = getValidationMessageForUserDetailsModification(user, existingUser);
        if (validationMessage != null) return validationMessage;
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(existingUser);
        return "User details modified successfully";
    }

    public String changePassword(Long userId, String currentPassword, String newPassword) {
        User existingUser = userRepository.findById(userId).orElse(null);
        String validationMessage = getValidationMessageForPasswordChange(currentPassword, newPassword, existingUser);
        if (validationMessage != null) return validationMessage;
        existingUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(existingUser);
        return "Password changed successfully";
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}