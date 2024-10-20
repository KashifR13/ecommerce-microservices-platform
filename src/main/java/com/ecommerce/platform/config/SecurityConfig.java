// src/main/java/com/ecommerce/platform/config/SecurityConfig.java
package com.ecommerce.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/public/**").permitAll() // Allow public access to certain endpoints
                        .anyRequest().authenticated() // Require authentication for other requests
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll) // Enable form login
                .logout(LogoutConfigurer::permitAll); // Enable logout

        return http.build();
    }
}