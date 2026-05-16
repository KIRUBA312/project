package com.example.rbacsystem.security;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64.Decoder;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.rbacsystem.constants.AppConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtill {

	private Key getSigningKey() {
		
		
		return Keys.hmacShaKeyFor(AppConstants.SECRET_KEY
				.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username)
				.setIssuedAt(new Date()).setExpiration(
						new Date(System.currentTimeMillis()
								+AppConstants.JWT_EXPIRATION
								))
				.signWith(getSigningKey(),
						SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	private Claims extractClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build().parseClaimsJws(token).getBody();
	}
	
	public boolean validateToken(String token) {
		try {
			extractClaims(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
}
