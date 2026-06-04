package com.example.airbnbbooking.service;


import java.util.List;


import com.example.airbnbbooking.dto.UserRequestDto;
import com.example.airbnbbooking.dto.UserResponseDto;

public interface UserService {

	UserResponseDto createUser(UserRequestDto dto);

	List<UserResponseDto> getAllUsers();

	UserResponseDto getUserById(Long id);

	UserResponseDto updateUser(Long id, UserRequestDto dto);

	String deletedUser(Long id);

}
