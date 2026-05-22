package com.example.financialservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.financialservice.dto.JwtResponseDto;
import com.example.financialservice.dto.LoginRequestDto;
import com.example.financialservice.security.JwtUtil;
import com.example.financialservice.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public JwtResponseDto login(LoginRequestDto dto) {
		// TODO Auto-generated method stub
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getUsername(), dto.getPassword()));
		String token = jwtUtil.generateToken(dto.getUsername());
		return new JwtResponseDto(token);
	}
	
	
	

}
