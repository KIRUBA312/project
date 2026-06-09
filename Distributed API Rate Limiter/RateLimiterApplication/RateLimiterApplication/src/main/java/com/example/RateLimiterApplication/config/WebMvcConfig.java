package com.example.RateLimiterApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.RateLimiterApplication.interceptor.RateLimitInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	private RateLimitInterceptor rateLimitInterceptor;
	
	public void addInterceptors(
			InterceptorRegistry registry) {
		registry.addInterceptor(rateLimitInterceptor)
		.addPathPatterns("/api/**");
	}
	
}
