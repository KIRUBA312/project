package com.example.enterprise_iam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.entity.RefreshToken;
import com.example.enterprise_iam.exception.ResourceNotFoundException;
import com.example.enterprise_iam.repository.RefreshTokenRepository;
import com.example.enterprise_iam.service.TokenRevocationService;

@Service
public class TokenRevocationServiceImpl implements TokenRevocationService{

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Override
	public void revokeRefreshToken(String Token) {
		// TODO Auto-generated method stub
		RefreshToken refreshToken = refreshTokenRepository
				.findByToken(Token)
				.orElseThrow(() ->
				new ResourceNotFoundException(
						"Refresh Token not found"));
		
		refreshToken.setRevoked(true);
		refreshTokenRepository.save(refreshToken);
		
	}

	@Override
	public boolean isRefreshTokenRevoked(String Token) {
		// TODO Auto-generated method stub
		return refreshTokenRepository.findByToken(Token)
				.map(RefreshToken::getRevoked).orElse(true);
		
	}
	
	
}
