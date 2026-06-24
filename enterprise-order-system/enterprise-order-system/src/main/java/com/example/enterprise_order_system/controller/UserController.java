package com.example.enterprise_order_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enterprise_order_system.dto.UserRequestDto;
import com.example.enterprise_order_system.dto.UserResponseDto;
import com.example.enterprise_order_system.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(
			@Valid @RequestBody UserRequestDto dto){
		return new ResponseEntity<>(
				userService.createUser(dto),HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUserById(
			@PathVariable Long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(
			@PathVariable Long id,
			@RequestBody UserRequestDto request){
		return ResponseEntity.ok(userService
				.updateUser(id,request));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(
			@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.ok("User Deleted Successfully");
	}
	
}
