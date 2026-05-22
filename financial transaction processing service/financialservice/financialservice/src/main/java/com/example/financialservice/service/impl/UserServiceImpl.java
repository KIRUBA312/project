package com.example.financialservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.financialservice.dto.UserRequestDto;
import com.example.financialservice.entity.User;
import com.example.financialservice.repository.UserRepository;
import com.example.financialservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String registerUser(UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(
				dto.getPassword()));
		user.setRole(dto.getRole());
		userRepository.save(user);
		
		return "User Registered Successfully";
	}
	
	
	
}
