package com.example.auth_server.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

	private static final int MAX_ATTEMPT = 5;
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	public void loginFailed(String username) {
		String key ="LOGIN_ATTEMPT: "+username;
		Long attempts = redisTemplate.opsForValue().increment(key);
		redisTemplate.expire(key, 15, TimeUnit.MINUTES);
	}
	public boolean isBlocked(String username) {
		String key = "LOGIN_ATTEMPT:"+username;
		String count = redisTemplate.opsForValue().get(key);
		if (count == null) {
			return false;
		}
		return Integer.parseInt(count) >=MAX_ATTEMPT ;
		
	}
	public void loginSucceeded(String username) {
		redisTemplate.delete("LOGIN_ATTEMPT:"+username);
	}
	
}
