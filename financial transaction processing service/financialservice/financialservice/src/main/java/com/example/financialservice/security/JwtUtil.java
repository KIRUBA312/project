package com.example.financialservice.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.financialservice.constants.AppConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final Key key = Keys.hmacShaKeyFor(
			AppConstants.SECRET_KEY.getBytes());
	
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(
						System.currentTimeMillis()
						+AppConstants.JWT_EXPIRATION))
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
	}
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}
	
	public boolean validateToken(
			String token, String username) {
		String extractedUsername = extractUsername(token);
		return extractedUsername.equals(username)
				&& !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return extractClaims(token)
				.getExpiration().before(new Date());
	}
	private Claims extractClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build().parseClaimsJws(token)
				.getBody();
	}
	
}
