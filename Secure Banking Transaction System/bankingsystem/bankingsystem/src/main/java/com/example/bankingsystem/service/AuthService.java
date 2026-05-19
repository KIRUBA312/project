package com.example.bankingsystem.service;

import org.jspecify.annotations.Nullable;

import com.example.bankingsystem.dto.JwtResponseDto;
import com.example.bankingsystem.dto.LoginRequestDto;
import com.example.bankingsystem.entity.User;

public interface AuthService {

	JwtResponseDto login(LoginRequestDto dto);
	
	User register(User user);

}
