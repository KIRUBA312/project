package com.example.stocktradingengine.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.stocktradingengine.dto.UserRequestDto;
import com.example.stocktradingengine.dto.UserResponseDto;

public interface UserService {

	UserResponseDto createUser(UserRequestDto dto);

	List<UserResponseDto> getAllUsers();

	UserResponseDto getUserById(Long id);

	UserResponseDto updateUser(Long id, UserRequestDto dto);

	String deleteUser(Long id);

}
