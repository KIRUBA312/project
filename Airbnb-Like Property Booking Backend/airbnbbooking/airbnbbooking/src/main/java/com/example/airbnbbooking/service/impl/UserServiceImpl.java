package com.example.airbnbbooking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airbnbbooking.dto.UserRequestDto;
import com.example.airbnbbooking.dto.UserResponseDto;
import com.example.airbnbbooking.entity.User;
import com.example.airbnbbooking.enums.UserRole;
import com.example.airbnbbooking.repository.UserRepository;
import com.example.airbnbbooking.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponseDto createUser(UserRequestDto dto) {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email already exists");
		}
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setRole(UserRole.valueOf(dto.getRole().toUpperCase()));
		user.setCreatedAt(LocalDateTime.now());
		
		user = userRepository.save(user);
		return maptoresponse(user);
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}

	@Override
	public UserResponseDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() ->
					new RuntimeException("User not found with id: " + id));
		return maptoresponse(user);
	}

	@Override
	public UserResponseDto updateUser(Long id, UserRequestDto dto) {
		// TODO Auto-generated method stub
		 User user = userRepository.findById(id)
				 .orElseThrow(() ->
					 new RuntimeException("User not found with id: " + id));
		 
		 
		 
		 user.setName(dto.getName());
		 user.setEmail(dto.getEmail());
		 user.setRole(UserRole.valueOf(dto.getRole().toUpperCase()));
		 
		 user = userRepository.save(user);
		 return maptoresponse(user);
		
	}

	@Override
	public String deletedUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() ->
					new RuntimeException("User not found with id: " + id));
		userRepository.delete(user);
		return "User deleted successfully";
	}
	
	private UserResponseDto maptoresponse(User user) {
		UserResponseDto dto = new UserResponseDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setRole(user.getRole().name());
		dto.setCreatedAt(user.getCreatedAt());
		return dto;
	}
	
}
