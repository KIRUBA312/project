package com.example.auth_server.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth_server.entity.RefreshToken;
import com.example.auth_server.entity.User;
import com.example.auth_server.exception.InvalidTokenException;
import com.example.auth_server.repository.RefreshTokenRepository;
import com.example.auth_server.service.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{

	@Autowired
	private RefreshTokenRepository repository;

	@Override
	public RefreshToken createRefreshToken(User user) {
		// TODO Auto-generated method stub
		RefreshToken token = new RefreshToken();
		token.setUser(user);
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(LocalDateTime.now()
				.plusDays(7));
		token.setRevoked(false);
		
		return repository.save(token);
	}

	@Override
	public RefreshToken verifyRefreshToken(String Token) {
		// TODO Auto-generated method stub
		RefreshToken refreshToken = repository.findByToken(Token)
				.orElseThrow(() -> new InvalidTokenException(
						"Invalid refresh token"));
		if(Boolean.TRUE.equals(refreshToken.getRevoked())) {
			throw new InvalidTokenException("Token revoked");
		}
		if(refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new InvalidTokenException("Token expired");
		}
			
		return refreshToken;
	}

	@Override
	public void revokeRefreshToken(String Token) {
		// TODO Auto-generated method stub
		RefreshToken refreshToken = verifyRefreshToken(Token);
		refreshToken.setRevoked(true);
		repository.save(refreshToken);
		
	}
	
}
