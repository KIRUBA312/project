package com.example.resource_service.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String SECRET = 
			"mysecretkeymysecretkeymysecretkeymysecretkey";
	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes(
				StandardCharsets.UTF_8));
	}
	public String extractUsername(String token) {
		Claims claims =
				Jwts.parser().verifyWith(getKey())
				.build().parseSignedClaims(token).getPayload();
		
		return claims.getSubject();
	}
	
}
