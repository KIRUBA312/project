package com.example.enterprise_iam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.enterprise_iam.dto.request.ForgotPasswordRequestDto;
import com.example.enterprise_iam.dto.request.LoginRequestDto;
import com.example.enterprise_iam.dto.request.RefreshTokenRequestDto;
import com.example.enterprise_iam.dto.request.ResetPasswordRequestDto;
import com.example.enterprise_iam.dto.request.UserRegistrationRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.JwtResponseDto;
import com.example.enterprise_iam.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponseDto> register(
			@Valid @RequestBody UserRegistrationRequestDto request){
		return new ResponseEntity<>(authService.register(request),
				HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponseDto> login(@Valid
			@RequestBody LoginRequestDto request){
		
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping("/refresh-token")
	public ResponseEntity<JwtResponseDto> refreshToken(@Valid
			@RequestBody RefreshTokenRequestDto request){
		
		return ResponseEntity.ok(authService.refreshToken(request));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<ApiResponseDto> logout(
			@RequestHeader("Authorization") String token){
		return ResponseEntity.ok(authService.logout(token));
	}
	
	@GetMapping("/verify")
	public ResponseEntity<ApiResponseDto> verifyEmail(
			@RequestParam String token){
		return ResponseEntity.ok(authService.verifyEmail(token));
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<ApiResponseDto> forgotPassword(@Valid
			@RequestBody ForgotPasswordRequestDto request){
		return ResponseEntity.ok(authService.forgotPassword(request));
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<ApiResponseDto> resetPassword(@Valid
			@RequestBody ResetPasswordRequestDto request){
		return ResponseEntity.ok(authService.resetPassword(request));
	}
	
	
}
