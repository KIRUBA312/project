package com.example.auth_server.service;

import com.example.auth_server.dto.LoginRequestDto;
import com.example.auth_server.dto.LoginResponseDto;
import com.example.auth_server.dto.RefreshTokenRequestDto;
import com.example.auth_server.dto.RefreshTokenResponseDto;
import com.example.auth_server.dto.UserRequestDto;
import com.example.auth_server.dto.UserResponseDto;

public interface AuthService {

	LoginResponseDto login(LoginRequestDto dto);

	UserResponseDto register(UserRequestDto dto);

	RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto dto);

	String logout(String refreshToken);

}
