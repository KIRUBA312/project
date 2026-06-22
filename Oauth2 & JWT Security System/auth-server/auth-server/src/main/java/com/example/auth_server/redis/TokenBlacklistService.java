package com.example.auth_server.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	public void blacklistToken(String token,long expirationTime) {
		redisTemplate.opsForValue().set(token, "BLACKLISTED",
				expirationTime,TimeUnit.MICROSECONDS);
	}
	public boolean isBlacklisted(String token) {
		return redisTemplate.hasKey(token);
	}
}
