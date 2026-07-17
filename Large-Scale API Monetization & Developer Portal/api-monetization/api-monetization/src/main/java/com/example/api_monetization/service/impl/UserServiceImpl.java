package com.example.api_monetization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api_monetization.dto.user.UserRequest;
import com.example.api_monetization.dto.user.UserResponse;
import com.example.api_monetization.entity.User;
import com.example.api_monetization.enums.AccountStatus;
import com.example.api_monetization.exception.ResourceNotFoundException;
import com.example.api_monetization.mapper.UserMapper;
import com.example.api_monetization.repository.UserRepository;
import com.example.api_monetization.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserResponse> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream()
				.map(userMapper::toResponse).toList();
	}
	@Override
	public UserResponse getUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("User not found"));
		return userMapper.toResponse(user);
	}
	@Override
	public UserResponse updateUser(Long id, @Valid UserRequest request) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("User not found"));
		userMapper.updateEntity(request, user);
		User updatedUser = userRepository.save(user);
		return userMapper.toResponse(updatedUser);
	}
	@Override
	public void updateStatus(Long id, AccountStatus active) {
		// TODO Auto-generated method stub
		 User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found."));

        user.setAccountStatus(active);

        userRepository.save(user);
	}
	
}
