package com.example.RateLimiterApplication.repository;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRateLimitRepository {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public Long incrementCounter(String key, long windowSeconds) {
		Long count = redisTemplate.opsForValue().increment(key);
		
		if (count != null && count ==1) {
			redisTemplate.expire(key, Duration.ofSeconds(windowSeconds));
		}
		
		return count;
	}
	
	public Long getCurrentCount(String key) {
		
		Object value = redisTemplate.opsForValue().get(key);
		
		if (value == null) {
			return 0L;
			
		}
		return Long.valueOf(value.toString());
	}
	public void deleteCounter(String key) {
		redisTemplate.delete(key);
	}
	public Long getRemainingTTL(String key) {
		Long ttl = redisTemplate.getExpire(key);
		
		return ttl == null ? 0L : ttl;
	}
	public boolean keyExists(String key) {
		Boolean exists = redisTemplate.hasKey(key);
		
		return exists != null && exists;
	}
	public void setValue(String key, Object value, long windowSeconds) {
		redisTemplate.opsForValue().set(key,value,Duration.ofSeconds(windowSeconds));
	}
	
	public Object getValue(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
}
