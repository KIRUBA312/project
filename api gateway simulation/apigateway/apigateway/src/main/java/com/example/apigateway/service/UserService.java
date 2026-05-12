package com.example.apigateway.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;

import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;

public interface UserService {

	UserResponseDto createUser(UserRequestDto userRequestDto);
	UserResponseDto getUserById(Long id);
	List<UserResponseDto> getAllUsers();
	UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
	void deleteUser(Long id);

}
