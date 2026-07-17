package com.example.api_monetization.service;

import com.example.api_monetization.dto.auth.LoginRequest;
import com.example.api_monetization.dto.auth.LoginResponse;
import com.example.api_monetization.dto.auth.RegisterRequest;
import com.example.api_monetization.dto.user.UserResponse;

import jakarta.validation.Valid;

public interface AuthService {

	UserResponse register(@Valid RegisterRequest request);

	LoginResponse login(@Valid LoginRequest request);

}
