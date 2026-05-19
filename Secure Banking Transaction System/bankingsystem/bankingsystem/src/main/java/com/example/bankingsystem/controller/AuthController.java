package com.example.bankingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingsystem.dto.JwtResponseDto;
import com.example.bankingsystem.dto.LoginRequestDto;
import com.example.bankingsystem.entity.User;
import com.example.bankingsystem.repository.UserRepository;
import com.example.bankingsystem.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponseDto> login(
			@RequestBody LoginRequestDto dto){
		
		return ResponseEntity.ok(authService.login(dto));
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(
			@RequestBody User user){
		user.setPassword(
				new BCryptPasswordEncoder()
				.encode(user.getPassword()));
		
		return ResponseEntity.ok(
				userRepository.save(user));
	}

}
