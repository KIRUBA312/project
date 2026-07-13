package com.example.enterprise_iam.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;

import static org.mockito.Mockito.mock;

import com.example.enterprise_iam.security.JwtAuthenticationFilter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@TestConfiguration
public class TestSecurityConfig {

    @Bean
    SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return mock(JwtAuthenticationFilter.class);
    }
}