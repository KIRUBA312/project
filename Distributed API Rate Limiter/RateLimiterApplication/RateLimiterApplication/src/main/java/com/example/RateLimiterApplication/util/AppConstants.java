package com.example.RateLimiterApplication.util;

public class AppConstants {

	private AppConstants() {}
	
	public static final String API_KEY_HEADER =
			"X-API-KEY";
	public static final String REDIS_RATE_LIMIT_PREFIX =
			"rate_limit:apikey:";
	public static final String REDIS_USER_PREFIX =
			"rate_limit:user:";
	public static final Integer DEFAULT_REQUEST_LIMIT = 100;
	public static final Integer DEFAULT_WINDOW_SECONDS = 60;
	public static final Integer PREMIUM_REQUEST_LIMIT = 500;
	public static final String RATE_LIMIT_EXCEEDED =
			"Rate limit exceeded";
	public static final String ANONYMOUS_USER = "anonymous";
	
}
