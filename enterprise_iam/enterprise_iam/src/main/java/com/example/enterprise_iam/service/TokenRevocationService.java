package com.example.enterprise_iam.service;

public interface TokenRevocationService {

	void revokeRefreshToken(String refreshToken);
	
	boolean isRefreshTokenRevoked(String refreshToken);
}
