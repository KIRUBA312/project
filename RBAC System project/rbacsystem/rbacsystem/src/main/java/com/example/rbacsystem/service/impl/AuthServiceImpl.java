package com.example.rbacsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.rbacsystem.dto.JwtResponseDto;
import com.example.rbacsystem.dto.LoginRequestDto;
import com.example.rbacsystem.entity.User;
import com.example.rbacsystem.exception.ResourceNotFoundException;
import com.example.rbacsystem.repository.UserRepository;
import com.example.rbacsystem.security.JwtUtill;
import com.example.rbacsystem.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtill jwtUtill;
	@Override
	public JwtResponseDto login(LoginRequestDto dto) {
		// TODO Auto-generated method stub
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto
						.getUsername(), dto.getPassword()));
		
		String token = jwtUtill.generateToken(dto.getUsername());
		
		return new JwtResponseDto(token);
	}
	
	

}
