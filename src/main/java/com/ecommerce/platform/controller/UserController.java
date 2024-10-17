package com.ecommerce.platform.controller;

import com.ecommerce.platform.model.User;
import com.ecommerce.platform.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public String updateUser(@RequestBody User user, @PathVariable Long userId) {
        return userService.modifyUserDetails(user, userId);
    }

    @PutMapping("/password")
    public String changePassword(@RequestParam Long userId, @RequestParam String currentPassword, @RequestParam String newPassword) {
        return userService.changePassword(userId, currentPassword, newPassword);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

}