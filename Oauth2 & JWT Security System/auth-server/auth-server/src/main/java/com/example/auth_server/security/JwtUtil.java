package com.example.auth_server.security;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtil {

	private static final String SECRET =
			"mysecretkeymysecretkeymysecretkeymysecretkey";
	private static final long ACCESS_TOKEN_EXPIRATION = 
			1000 * 60 * 60;
	private static final long REFRESH_TOKEN_EXPIRATION =
			1000L * 60 * 60 * 24 * 7;
	
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(
				SECRET.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(String username, 
			String tenantName, List<String> roles) {
		// TODO Auto-generated method stub
		return Jwts.builder().subject(username)
				.claim("tenant", tenantName)
				.claim("roles", roles)
				.issuedAt(new Date())
				.expiration(new Date(
						System.currentTimeMillis()
						+ ACCESS_TOKEN_EXPIRATION))
				.signWith(getSigningKey())
				.compact();
	}
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	public String extractTenant(String token) {
		return extractAllClaims(token).get("tenant",String.class);
	}
	public boolean validateToken(String token,String username) {
		String extractedUsername = extractUsername(token);
		return extractedUsername.equals(username)&&!isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractAllClaims(token).getExpiration()
				.before(new Date());
	}

	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().verifyWith(getSigningKey())
				.build().parseSignedClaims(token).getPayload();
	}
	
}
