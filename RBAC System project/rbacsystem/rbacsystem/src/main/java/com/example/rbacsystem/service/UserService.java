package com.example.rbacsystem.service;



import java.util.List;


import com.example.rbacsystem.dto.UserRequestDto;
import com.example.rbacsystem.dto.UserResponseDto;

public interface UserService {

	
	UserResponseDto createUser(UserRequestDto dto);

	List<UserResponseDto> getAllUsers();

	UserResponseDto getUserById(Long id);

	UserResponseDto updateUser(Long id, UserRequestDto dto);

	void deleteUser(Long id);
	

	

}
