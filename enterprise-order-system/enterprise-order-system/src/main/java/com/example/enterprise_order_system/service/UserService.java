package com.example.enterprise_order_system.service;

import java.util.List;

import com.example.enterprise_order_system.dto.UserRequestDto;
import com.example.enterprise_order_system.dto.UserResponseDto;

import jakarta.validation.Valid;

public interface UserService {

	UserResponseDto createUser(@Valid UserRequestDto dto);

	UserResponseDto getUserById(Long id);

	List<UserResponseDto> getAllUsers();

	UserResponseDto updateUser(Long id, UserRequestDto request);

	void deleteUser(Long id);

}
