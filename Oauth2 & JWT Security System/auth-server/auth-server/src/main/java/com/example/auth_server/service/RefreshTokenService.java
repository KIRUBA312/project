package com.example.auth_server.service;

import com.example.auth_server.entity.RefreshToken;
import com.example.auth_server.entity.User;

public interface RefreshTokenService {

	RefreshToken createRefreshToken(User user);

	RefreshToken verifyRefreshToken(String refreshToken);

	void revokeRefreshToken(String refreshToken);

}
