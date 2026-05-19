package com.example.bankingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.dto.JwtResponseDto;
import com.example.bankingsystem.dto.LoginRequestDto;
import com.example.bankingsystem.entity.User;
import com.example.bankingsystem.repository.UserRepository;
import com.example.bankingsystem.security.JwtUtil;
import com.example.bankingsystem.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public JwtResponseDto login(LoginRequestDto dto) {
		// TODO Auto-generated method stub
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.getUsername(),
						dto.getPassword()));
		String token = jwtUtil.generateToken(dto.getUsername());
		 
		return new JwtResponseDto(token);
	}

	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user); 
	}
	
	

}
