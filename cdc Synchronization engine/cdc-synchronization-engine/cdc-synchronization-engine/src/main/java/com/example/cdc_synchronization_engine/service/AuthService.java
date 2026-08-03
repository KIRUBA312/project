package com.example.cdc_synchronization_engine.service;


import com.example.cdc_synchronization_engine.dto.LoginRequest;
import com.example.cdc_synchronization_engine.dto.LoginResponse;
import com.example.cdc_synchronization_engine.dto.RegisterRequest;
import com.example.cdc_synchronization_engine.dto.RegisterResponse;

import jakarta.validation.Valid;

public interface AuthService {

	RegisterResponse register(@Valid RegisterRequest request);

	LoginResponse login(@Valid LoginRequest request);

	RegisterResponse getCurrentUser();

}
