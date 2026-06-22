package com.example.auth_server.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider implements JwtService{
	
	private static final String SECRET_KEY =
			"mysecretkeymysecretkeymysecretkeymysecretkey";
	private final SecretKey key = Keys.hmacShaKeyFor(
			SECRET_KEY.getBytes());
	@Override
	public String generateToken(String username) {
		// TODO Auto-generated method stub
		return Jwts.builder().subject(username)
				.issuedAt(new Date()).expiration(
						new Date(System.currentTimeMillis()
								+3600000))
				.signWith(key)
				.compact();
	}
	@Override
	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		Claims claims = Jwts.parser().verifyWith(key).build()
				.parseSignedClaims(token).getPayload();
		return claims.getSubject();
	}
	@Override
	public boolean validateToken(String token) {
		// TODO Auto-generated method stub
		try {
			Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	

}
