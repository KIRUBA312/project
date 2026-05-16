package com.example.rbacsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rbacsystem.dto.JwtResponseDto;
import com.example.rbacsystem.dto.LoginRequestDto;
import com.example.rbacsystem.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponseDto> login(
			@RequestBody LoginRequestDto dto){
		
		return ResponseEntity.ok(authService.login(dto));
		
	}

}
