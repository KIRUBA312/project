package com.example.taskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagement.dto.ApiResponseDto;
import com.example.taskmanagement.dto.UserRequestDto;
import com.example.taskmanagement.dto.UserResponseDto;
import com.example.taskmanagement.service.UserService;
import com.example.taskmanagement.util.AppConstants;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(
			@Validated @RequestBody UserRequestDto dto){
		
		return new ResponseEntity<>(userService.createUser(dto),
				HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUserById(
			@PathVariable Long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(
			@PathVariable Long id,@RequestBody UserRequestDto dto){
		
		return ResponseEntity.ok(userService.updateUser(id,dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto> deleteUser(
			@PathVariable Long id){
		userService.deleteUser(id);
		
		return ResponseEntity.ok(
				new ApiResponseDto(AppConstants.USER_DELETED,200));
	}
	
}
