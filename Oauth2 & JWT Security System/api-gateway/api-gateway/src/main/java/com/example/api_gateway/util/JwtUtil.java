package com.example.api_gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtil {

	private static final String SECRET = "mysecretkeymysecretkeymysecretkey";
	
	public static Claims extractClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	public static String getUsername(String token) {
		return extractClaims(token).getSubject();
	}
	
}
