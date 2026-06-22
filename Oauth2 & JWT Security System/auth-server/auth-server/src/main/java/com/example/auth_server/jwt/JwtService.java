package com.example.auth_server.jwt;

public interface JwtService {

	String generateToken(String username);
	String extractUsername(String token);
	boolean validateToken(String token);
}
