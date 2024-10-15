package com.ecommerce.platform.controller;

import com.ecommerce.platform.model.User;
import com.ecommerce.platform.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{userId}")
    public String updateUser(@RequestBody User user, @PathVariable Long userId) {
        return userService.modifyUserDetails(user, userId);
    }

    @PutMapping("/password")
    public String changePassword(@RequestBody User user) {
        return userService.changePassword(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}