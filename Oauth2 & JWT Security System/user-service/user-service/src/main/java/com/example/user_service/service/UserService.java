package com.example.user_service.service;

import java.util.List;

import com.example.user_service.dto.UserRequestDto;
import com.example.user_service.dto.UserResponseDto;

public interface UserService {

	UserResponseDto createUser(UserRequestDto dto);

	UserResponseDto getUserById(Long id);

	List<UserResponseDto> getAllUsers();

	UserResponseDto updateUser(Long id, UserRequestDto dto);

	String deleteUser(Long id);

}
