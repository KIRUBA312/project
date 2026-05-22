package com.example.financialservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financialservice.dto.JwtResponseDto;
import com.example.financialservice.dto.LoginRequestDto;
import com.example.financialservice.dto.UserRequestDto;
import com.example.financialservice.service.AuthService;
import com.example.financialservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String>
	registerUser(
			@RequestBody UserRequestDto dto){
		
		return ResponseEntity.ok(userService.registerUser(dto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponseDto> login(
			@Validated
			@RequestBody
			LoginRequestDto dto){
		
		return ResponseEntity.ok(authService.login(dto));
	}
	
}
