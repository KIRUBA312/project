package com.example.auth_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_server.dto.LoginRequestDto;
import com.example.auth_server.dto.LoginResponseDto;
import com.example.auth_server.dto.UserRequestDto;
import com.example.auth_server.dto.UserResponseDto;
import com.example.auth_server.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> register(
			@RequestBody UserRequestDto dto){
		return ResponseEntity.ok(authService.register(dto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(
			@RequestBody LoginRequestDto dto){
		return ResponseEntity.ok(authService.login(dto));
	}
	
}
