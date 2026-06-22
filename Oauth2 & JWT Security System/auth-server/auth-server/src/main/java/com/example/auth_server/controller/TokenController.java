package com.example.auth_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_server.dto.RefreshTokenRequestDto;
import com.example.auth_server.dto.RefreshTokenResponseDto;
import com.example.auth_server.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class TokenController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/refresh")
	public ResponseEntity<RefreshTokenResponseDto> refreshToken(
			@RequestBody RefreshTokenRequestDto dto){
		return ResponseEntity.ok(
				authService.refreshToken(dto));
	}
	@PostMapping("/logout")
	public ResponseEntity<String> logout(
			@RequestParam String refreshToken){
		return ResponseEntity.ok(
				authService.logout(refreshToken));
		
		
	}
	
}
