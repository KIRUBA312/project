package com.example.api_monetization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_monetization.dto.user.UserRequest;
import com.example.api_monetization.dto.user.UserResponse;
import com.example.api_monetization.enums.AccountStatus;
import com.example.api_monetization.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(
			@PathVariable Long id){
		return ResponseEntity.ok(userService.getUser(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse>updateUser(@PathVariable Long id,
			@Valid @RequestBody UserRequest request){
		return ResponseEntity.ok(userService.updateUser(id, request));
	}
	@PatchMapping("/{id}/status")
	public ResponseEntity<String> updateUserStatus(
			@PathVariable Long id,@RequestParam AccountStatus active){
		userService.updateStatus(id,active);
		
		return ResponseEntity.ok(
				"User status updated Successfully");
	}
	
}
