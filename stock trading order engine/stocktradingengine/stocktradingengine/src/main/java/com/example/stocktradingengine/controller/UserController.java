package com.example.stocktradingengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stocktradingengine.dto.UserRequestDto;
import com.example.stocktradingengine.dto.UserResponseDto;
import com.example.stocktradingengine.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDto>
	createUser(@RequestBody UserRequestDto dto){
		
		return ResponseEntity.ok(userService.createUser(dto));
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
	public ResponseEntity<String> deleteUser(
			@PathVariable Long id){
		return ResponseEntity.ok(userService.deleteUser(id));
	}

}
