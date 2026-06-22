package com.example.auth_server.service;

import java.util.List;

import com.example.auth_server.dto.UserRequestDto;
import com.example.auth_server.dto.UserResponseDto;

public interface UserService {

	UserResponseDto createUser(UserRequestDto dto);

	List<UserResponseDto> getAllUsers();

	UserResponseDto getUserById(Long id);

	UserResponseDto updateUser(Long id, UserRequestDto dto);

	String deleteUser(Long id);

}
