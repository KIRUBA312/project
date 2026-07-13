package com.example.enterprise_iam.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secretKey;
	
	@Value("${jwt.access.expiration}")
	private long accessTokenExpiration;
	
	@Value("${jwt.refresh.expiration}")
	private long refreshTokenExpiration;
	
	public String generateAccessToken(UserDetails userDetails) {
		return createToken(new HashMap<>(),userDetails.getUsername(),
				accessTokenExpiration);
	}
	public String generateRefreshToken(UserDetails userDetails) {
		return createToken(new HashMap<>(), 
				userDetails.getUsername(),
				refreshTokenExpiration);
				
	}
	public String generateToken(Map<String, Object> claims,UserDetails userDetails,
			long expiration) {
		return createToken(claims, userDetails.getUsername(), expiration);
	}

	private String createToken(Map<String, Object> claims, String username, 
			long expiration) {
		// TODO Auto-generated method stub
		return Jwts.builder().claims(claims).subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(getSigningKey())
				.compact();
	}
	private Key getSigningKey() {
		// TODO Auto-generated method stub
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	public Date extractExpiration(String token){
		return extractClaim(token, Claims::getExpiration);
	}
	private <T> T extractClaim(String token, Function<Claims, T>claimsResolver) {
		Claims claims = extractAllClaims(token);
		// TODO Auto-generated method stub
		return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().verifyWith((javax.crypto.SecretKey) getSigningKey())
				.build().parseSignedClaims(token).getPayload();
	}
//	 private java.security.Key getSigningKey1() {
//
//	        byte[] keyBytes =
//	                io.jsonwebtoken.io.Decoders.BASE64.decode(secretKey);
//
//	        return io.jsonwebtoken.security.Keys.hmacShaKeyFor(keyBytes);
//
//	    }

	    /**
	     * Check whether token is expired
	     */
	    public boolean isTokenExpired(String token) {

	        return extractExpiration(token).before(new Date());

	    }

	    /**
	     * Validate JWT
	     */
	    public boolean isTokenValid(String token,
	                                UserDetails userDetails) {

	        String username = extractUsername(token);

	        return username.equals(userDetails.getUsername())
	                && !isTokenExpired(token);

	    }

	    /**
	     * Validate only expiration
	     */
	    public boolean validateToken(String token) {

	        try {

	            return !isTokenExpired(token);

	        } catch (Exception ex) {

	            return false;

	        }

	    }

	    /**
	     * Extract complete claims
	     */
	    public Claims getClaims(String token) {

	        return extractAllClaims(token);

	    }

	    /**
	     * Extract issued date
	     */
	    public Date getIssuedDate(String token) {

	        return extractClaim(token, Claims::getIssuedAt);

	    }

	    /**
	     * Extract expiration date
	     */
	    public Date getExpiryDate(String token) {

	        return extractClaim(token, Claims::getExpiration);

	    }
	
	
}
