package com.example.cdc_synchronization_engine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cdc_synchronization_engine.dto.LoginRequest;
import com.example.cdc_synchronization_engine.dto.LoginResponse;
import com.example.cdc_synchronization_engine.dto.RegisterRequest;
import com.example.cdc_synchronization_engine.dto.RegisterResponse;
import com.example.cdc_synchronization_engine.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(

            @Valid@RequestBody RegisterRequest request) {

        return new ResponseEntity<>(
                authService.register(request),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(

            @Valid
            @RequestBody
            LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<RegisterResponse> currentUser() {

        return ResponseEntity.ok(
                authService.getCurrentUser());
    }
}
