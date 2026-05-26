package com.example.taskmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanagement.dto.UserRequestDto;
import com.example.taskmanagement.dto.UserResponseDto;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.exception.DuplicateResourceException;
import com.example.taskmanagement.exception.ResourceNotFoundException;
import com.example.taskmanagement.repository.UserRepository;
import com.example.taskmanagement.service.UserService;
import com.example.taskmanagement.util.AppConstants;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponseDto createUser(UserRequestDto dto) {
		// TODO Auto-generated method stub
		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new DuplicateResourceException(
					AppConstants.DUPLICATE_EMAIL);
			
		}
		User user = new User();
		
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setRole(dto.getRole());
		user.setCreatedAt(LocalDateTime.now());
		
		User savedUser = userRepository.save(user);
		
		return maptoResponse(savedUser);
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}

	@Override
	public UserResponseDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.USER_NOT_FOUND));
		
		return maptoResponse(user);
	}

	@Override
	public UserResponseDto updateUser(Long id, UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.USER_NOT_FOUND));
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setRole(dto.getRole());
		
		User updatedUser = userRepository.save(user);
		
		return maptoResponse(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));
		
		userRepository.delete(user);
		
	}
	
	private UserResponseDto maptoResponse(User user) {
		
		UserResponseDto dto = new UserResponseDto();
		
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setRole(user.getRole());
		dto.setCreatedAt(user.getCreatedAt());
		
		return dto;
	}

}
