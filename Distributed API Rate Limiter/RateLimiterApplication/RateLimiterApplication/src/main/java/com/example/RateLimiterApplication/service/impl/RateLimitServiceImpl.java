package com.example.RateLimiterApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RateLimiterApplication.entity.RateLimitingConfig;
import com.example.RateLimiterApplication.repository.RateLimitConfigRepository;
import com.example.RateLimiterApplication.repository.RedisRateLimitRepository;
import com.example.RateLimiterApplication.service.RateLimitService;
import com.example.RateLimiterApplication.util.RateLimitUtil;

@Service
public class RateLimitServiceImpl implements RateLimitService{
	
	@Autowired
	private RedisRateLimitRepository redisRateLimitRepository;
	@Autowired
	private RateLimitConfigRepository configRepository;
	
	@Override
	public boolean isRequestAllowed(String apiKey, String endpoint) {
		// TODO Auto-generated method stub
		RateLimitingConfig config = configRepository.findByEndpoint(endpoint)
				.orElse(null);
		if (config == null) {
			return true;
		}
		String key = RateLimitUtil
				.buildApiKeyRedisKey(apiKey, endpoint);
		Long count = redisRateLimitRepository
				.incrementCounter(key, config.getWindowSeconds());
		
		return count <=config.getRequestLimit();
	}
	@Override
	public long getRemainingRequest(String apiKey, String endpoint) {
		// TODO Auto-generated method stub
		RateLimitingConfig config = configRepository.findByEndpoint(endpoint).orElse(null);
		if (config == null) {
			return 0;
		}
		String key = RateLimitUtil.buildApiKeyRedisKey(apiKey, endpoint);
		Long count = redisRateLimitRepository.getCurrentCount(key);
		return Math.max(0, config.getRequestLimit() - count);
	}
	@Override
	public long getResetTime(String apiKey, String endpoint) {
		// TODO Auto-generated method stub
		String key = RateLimitUtil.buildApiKeyRedisKey(apiKey, endpoint);
		return redisRateLimitRepository.getRemainingTTL(key);
	}
	@Override
	public int getRequestLimit(String endpoint) {
		// TODO Auto-generated method stub
		return configRepository.findByEndpoint(endpoint)
				.map(RateLimitingConfig::getRequestLimit)
				.orElse(100);
	}
	
	

}
