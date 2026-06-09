package com.example.RateLimiterApplication.service;

public interface RateLimitService {

	boolean isRequestAllowed(String apiKey,String endpoint);
	long getRemainingRequest(String apiKey,String endpoint);
	long getResetTime(String apiKey,String endpoint);
	int getRequestLimit(String endpoint);
	
}
