package com.example.rbacsystem.service;

import com.example.rbacsystem.dto.JwtResponseDto;
import com.example.rbacsystem.dto.LoginRequestDto;

public interface AuthService {

	JwtResponseDto login(LoginRequestDto dto);

}
