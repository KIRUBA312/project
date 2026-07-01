package com.example.coupon_discount_engine.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coupon_discount_engine.dto.UserRequestDto;
import com.example.coupon_discount_engine.dto.UserResponseDto;
import com.example.coupon_discount_engine.entity.User;
import com.example.coupon_discount_engine.exception.ResourceNotFoundException;
import com.example.coupon_discount_engine.repository.UserRepository;
import com.example.coupon_discount_engine.service.UserService;
import com.example.coupon_discount_engine.util.MapperUtil;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponseDto createUser(@Valid UserRequestDto request) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setStatus(request.getStatus());
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		return MapperUtil.toUserResponse(userRepository.save(user));
	}

	@Override
	public UserResponseDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("User not found with id :"+id));
		return MapperUtil.toUserResponse(user);
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream().map(MapperUtil::toUserResponse)
				.collect(Collectors.toList());
	}

	@Override
	public UserResponseDto updateUser(Long id, @Valid UserRequestDto request) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("User not found with id : "+id));
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setStatus(request.getStatus());
		user.setUpdatedAt(LocalDateTime.now());
		return MapperUtil.toUserResponse(userRepository.save(user));
		
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("User not found with id : "+id));
		userRepository.delete(user);
		
	}
	
	
}
