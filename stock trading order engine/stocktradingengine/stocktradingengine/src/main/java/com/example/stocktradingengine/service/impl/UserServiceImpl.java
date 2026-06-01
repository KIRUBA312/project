package com.example.stocktradingengine.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stocktradingengine.dto.UserRequestDto;
import com.example.stocktradingengine.dto.UserResponseDto;
import com.example.stocktradingengine.entity.User;
import com.example.stocktradingengine.exception.ResourceNotFoundException;
import com.example.stocktradingengine.repository.UserRepository;
import com.example.stocktradingengine.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponseDto createUser(UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = new User();
		
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setBalance(dto.getBalance());
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
				new ResourceNotFoundException("User not found"));
		return maptoresponse(user);
	}

	@Override
	public UserResponseDto updateUser(Long id, UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> 
				new ResourceNotFoundException("User not found"));
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setBalance(dto.getBalance());
		
		user = userRepository.save(user);
		return maptoresponse(user);
	}

	@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> 
				new ResourceNotFoundException("User not found"));
		userRepository.delete(user);
		return "User deleted successfully";
	}
	
	private UserResponseDto maptoresponse(User user) {
		
		UserResponseDto dto = new UserResponseDto();
		
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setBalance(user.getBalance());
		dto.setCreatedAt(user.getCreatedAt());
		
		return dto;
		
	}
	
}
