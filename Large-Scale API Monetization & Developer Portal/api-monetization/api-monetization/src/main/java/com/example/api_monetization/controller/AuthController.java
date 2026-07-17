package com.example.api_monetization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_monetization.dto.auth.LoginRequest;
import com.example.api_monetization.dto.auth.LoginResponse;
import com.example.api_monetization.dto.auth.RegisterRequest;
import com.example.api_monetization.dto.user.UserResponse;
import com.example.api_monetization.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@Valid
			@RequestBody RegisterRequest request){
		UserResponse response = authService.register(request);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid
			@RequestBody LoginRequest request){
		LoginResponse response = authService.login(request);
		return ResponseEntity.ok(response);
	}
	
	
	
}
