package com.example.RateLimiterApplication.util;

import com.example.RateLimiterApplication.entity.RateLimitingConfig;

public class RateLimitUtil {

	private RateLimitUtil() {}
	
	public static String buildApiKeyRedisKey(
			String apiKey,String endpoint) {
		return AppConstants.REDIS_RATE_LIMIT_PREFIX+apiKey
				+":"+endpoint;
	}
	public static String buildUserRedisKey(
			String userId,String endpoint) {
		return AppConstants.REDIS_USER_PREFIX
				+userId+":"+endpoint;
		
	}
	public static long calculateRemainingRequests(Long currentCount,
			Integer requestLimit) {
		if (currentCount == null) {
			return requestLimit;
		}
		
		return Math.max(0, requestLimit-currentCount);
	}
	public static boolean isLimitExceeded(Long currentCount,
			Integer requestLimit) {
		return currentCount > requestLimit;
	}
	public static int getApplicableLimit(
			RateLimitingConfig config,boolean premiumUser) {
		if (premiumUser && config.getPremiumLimit()!=null) {
			return config.getPremiumLimit();
		}
		return config.getRequestLimit();
		
	}
	
	
}
