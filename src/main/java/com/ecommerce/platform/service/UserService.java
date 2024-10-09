package com.ecommerce.platform.service;

import com.ecommerce.platform.model.User;
import com.ecommerce.platform.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService extends UserServiceBase {

    public UserService(UserRepository userRepository) {
        super(userRepository);
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
        String validationMessage = getValidationMessage(user, existingUser);
        if (validationMessage != null) return validationMessage;
        existingUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(existingUser);
        return "Password changed successfully";
    }

    public String modifyUserDetails(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        String validationMessage = getValidationMessage(user, existingUser);
        if (validationMessage != null) return validationMessage;
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(existingUser);
        return "User details modified successfully";
    }

}