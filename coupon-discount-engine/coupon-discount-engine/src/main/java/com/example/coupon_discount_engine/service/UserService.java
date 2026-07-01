package com.example.coupon_discount_engine.service;

import java.util.List;

import com.example.coupon_discount_engine.dto.UserRequestDto;
import com.example.coupon_discount_engine.dto.UserResponseDto;

import jakarta.validation.Valid;

public interface UserService {

	UserResponseDto createUser(@Valid UserRequestDto request);

	UserResponseDto getUserById(Long id);

	List<UserResponseDto> getAllUsers();

	UserResponseDto updateUser(Long id, @Valid UserRequestDto dto);

	void deleteUser(Long id);

}
