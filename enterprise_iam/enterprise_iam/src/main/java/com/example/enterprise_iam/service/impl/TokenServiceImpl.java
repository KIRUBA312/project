package com.example.enterprise_iam.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.dto.response.JwtResponseDto;
import com.example.enterprise_iam.entity.RefreshToken;
import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.exception.ResourceNotFoundException;
import com.example.enterprise_iam.repository.RefreshTokenRepository;
import com.example.enterprise_iam.service.TokenService;
import com.example.enterprise_iam.util.JwtUtil;

@Service
public class TokenServiceImpl implements TokenService
{
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Override
	public JwtResponseDto generateTokens(User user, UserDetails userDetails) {
		// TODO Auto-generated method stub
		String accessToken = jwtUtil.generateAccessToken(userDetails);
		String refreshToken= jwtUtil.generateRefreshToken(userDetails);
		
		RefreshToken token = new RefreshToken();
		
		token.setUser(user);
		token.setToken(refreshToken);
		token.setRevoked(false);
		token.setExpiryDate(LocalDateTime.now()
				.plusDays(7));
		
		refreshTokenRepository.save(token);
		JwtResponseDto dto = new JwtResponseDto();
		dto.setAccessToken(accessToken);
		dto.setRefreshToken(refreshToken);
		dto.setExpiresIn(900000L);
		
		return dto;
	}

	@Override
	public JwtResponseDto refreshAccessToken(String refreshToken) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Implement using AuthService");
	}

	@Override
	public void revokeRefreshToken(String refreshToken) {
		// TODO Auto-generated method stub
		
		RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
				.orElseThrow(() ->
				new ResourceNotFoundException("Refresh token not found"));
		
		token.setRevoked(true);
	}

}
