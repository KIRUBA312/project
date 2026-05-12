package com.example.apigateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.service.UserService;
import com.example.apigateway.util.ApiKeyValidator;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(
			@RequestBody UserRequestDto userRequestDto,
			@RequestHeader(value = "X-API-KEY", required = false)String apiKey){
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(userService.createUser(userRequestDto));
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAllUser(
			@RequestHeader(value = "X-API-KEY", required = false)String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUserById(
			@PathVariable Long id,
			@RequestHeader(value = "X-API-KEY", required = false)String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(userService.getUserById(id));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(
			@PathVariable Long id,@RequestBody UserRequestDto userRequestDto,
			@RequestHeader(value = "X-API-KEY",required = false)String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(
			@PathVariable Long id,
			@RequestHeader(value = "X-API-KEY",required = false)String apiKey){
	
		ApiKeyValidator.validate(apiKey);
		
		userService.deleteUser(id);
		
		return ResponseEntity.ok("User Deleted Successfully");
		
	}
	
}
