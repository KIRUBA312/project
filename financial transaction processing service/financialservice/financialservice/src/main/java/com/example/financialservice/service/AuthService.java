package com.example.financialservice.service;


import com.example.financialservice.dto.JwtResponseDto;
import com.example.financialservice.dto.LoginRequestDto;

public interface AuthService {

	JwtResponseDto login(LoginRequestDto dto);

}
