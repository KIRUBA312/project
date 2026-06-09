package com.example.RateLimiterApplication.interceptor;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.RateLimiterApplication.entity.RateLimitingConfig;
import com.example.RateLimiterApplication.exception.RateLimitExceededException;
import com.example.RateLimiterApplication.repository.RateLimitConfigRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor{
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private RateLimitConfigRepository configRepository;
	
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler)throws Exception{
		String endpoint = request.getRequestURI();
		String apiKey = request.getHeader("X-API-KEY");
		
		if (apiKey == null || apiKey.isBlank()) {
			apiKey = "anonymous";
		}
		System.out.println("=================================");
        System.out.println("Interceptor Hit");
        System.out.println("Endpoint : " + endpoint);
        System.out.println("API Key  : " + apiKey);
		RateLimitingConfig config = configRepository
				.findByEndpoint(endpoint)
				.orElse(null);
		if(config == null) {
			return true;
		}
		if(!Boolean.TRUE.equals(config.getIsActive())) {
			System.out.println("Rate limiting disabled");
			return true;
		}
		
		String redisKey ="rate_limit:apikey:"+apiKey+":"
				+endpoint;
		Long count = redisTemplate.opsForValue()
				.increment(redisKey);
		if (count == 1 && count != null) {
			
			redisTemplate.expire(redisKey, 
					Duration.ofSeconds(
							config.getWindowSeconds()));			
		}
		
		int limit = config.getRequestLimit();
		long remaining = Math.max(0, limit-count);
		Long ttl = redisTemplate.getExpire(redisKey);
		response.setHeader("X-RateLimit-Limit",String.valueOf(limit));
		response.setHeader("X-RateLimit-Remaining",String.valueOf(remaining));
		response.setHeader("X-RateLimit-Reset",String.valueOf(ttl));
		System.out.println("Redis Key : " + redisKey);
        System.out.println("Count     : " + count);
        System.out.println("Remaining : " + remaining);
        System.out.println("TTL       : " + ttl);
		if (count > limit) {
			
			throw new RateLimitExceededException("Rate limit exceeded");
			
		}
		
		return true;
		
	}

}
