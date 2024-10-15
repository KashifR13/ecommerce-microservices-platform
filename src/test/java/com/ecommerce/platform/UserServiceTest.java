package com.ecommerce.platform;

import com.ecommerce.platform.model.User;
import com.ecommerce.platform.repository.UserRepository;
import com.ecommerce.platform.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void initializeUser() {
        user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
    }

    @Test
    public void shouldRegisterUserSuccessfully() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        String result = userService.register(user);
        assertEquals("User registered successfully", result, "User should be registered successfully");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void shouldNotRegisterUserIfAlreadyExists() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        String result = userService.register(user);
        assertEquals("User already exists", result, "User should not be registered if already exists");
    }

    @Test
    public void shouldChangePasswordSuccessfully() {
        User existingUser = new User();
        existingUser.setUsername("testUser");
        existingUser.setPassword(BCrypt.hashpw("oldPassword", BCrypt.gensalt()));

        when(userRepository.findByUsername(user.getUsername())).thenReturn(existingUser);
        user.setPassword("newPassword");
        String result = userService.changePassword(user);
        assertEquals("Password changed successfully", result, "Password should be changed successfully");
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void shouldNotChangePasswordIfUserNotFound() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        String result = userService.changePassword(user);
        assertEquals("User not found", result, "Password should not be changed if user is not found");
    }

    @Test
    public void shouldLoginSuccessfully() {
        User existingUser = new User();
        existingUser.setUsername("testUser");
        existingUser.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));

        when(userRepository.findByUsername(user.getUsername())).thenReturn(existingUser);
        String result = userService.login(user);
        assertEquals("User logged in successfully", result, "User should log in successfully");
    }

    @Test
    public void shouldNotLoginWithInvalidCredentials() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        String result = userService.login(user);
        assertEquals("Invalid username or password", result, "User should not log in with invalid credentials");
    }

    @Test
    public void shouldDeleteUserSuccessfully() {
        doNothing().when(userRepository).deleteById(user.getUserId());
        String result = userService.deleteUser(user.getUserId());
        assertEquals("User deleted successfully", result, "User should be deleted successfully");
        verify(userRepository, times(1)).deleteById(user.getUserId());
    }

    @Test
    public void shouldModifyUserDetailsSuccessfully() {
        User existingUser = new User();
        existingUser.setUsername("testUser");
        existingUser.setPassword(BCrypt.hashpw("oldPassword", BCrypt.gensalt()));
        existingUser.setEmail("oldEmail@example.com");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(existingUser);
        user.setPassword("newPassword");
        user.setEmail("newEmail@example.com");
        String result = userService.modifyUserDetails(user, user.getUserId());
        assertEquals("User details modified successfully", result, "User details should be modified successfully");
        verify(userRepository, times(1)).save(existingUser);
    }
}