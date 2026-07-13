package com.example.enterprise_iam.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.enterprise_iam.dto.response.JwtResponseDto;
import com.example.enterprise_iam.entity.User;

public interface TokenService {

	JwtResponseDto generateTokens(User user, UserDetails userDetails);
	JwtResponseDto refreshAccessToken(String refreshToken);
	void revokeRefreshToken(String refreshToken);
}
