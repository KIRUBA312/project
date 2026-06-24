package com.example.enterprise_order_system.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_order_system.dto.UserRequestDto;
import com.example.enterprise_order_system.dto.UserResponseDto;
import com.example.enterprise_order_system.entity.User;
import com.example.enterprise_order_system.exception.ResourceNotFoundException;
import com.example.enterprise_order_system.repository.UserRepository;
import com.example.enterprise_order_system.service.UserService;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponseDto createUser(@Valid UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = new User();		
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setMobile(dto.getMobile());
		user.setCreatedAt(LocalDateTime.now());
		User savedUser = userRepository.save(user);
		return maptoresponse(savedUser);
	}

	@Override
	public UserResponseDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException(
				"User not found with id : "+id));
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
	public UserResponseDto updateUser(Long id, UserRequestDto request) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException(
				"USER not found with id : "+id));
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		User updatedUser = userRepository.save(user);
		return maptoresponse(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException(
				"User not found with id : "+id));
		userRepository.delete(user);
		
	}
	private UserResponseDto maptoresponse(User user) {
		UserResponseDto response = new UserResponseDto();
		response.setId(user.getId());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setMobile(user.getMobile());
		
		return response;
		
	}

}
