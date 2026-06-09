package com.example.RateLimiterApplication.service;

import java.util.List;


import com.example.RateLimiterApplication.dto.RateLimitConfigRequestDto;
import com.example.RateLimiterApplication.dto.RateLimitConfigResponseDto;

public interface RateLimitConfigService {

	RateLimitConfigResponseDto createConfig(RateLimitConfigRequestDto dto);

	List<RateLimitConfigResponseDto> getAllConfig();

	RateLimitConfigResponseDto getConfigById(Long id);

	RateLimitConfigResponseDto updateConfig(Long id, RateLimitConfigRequestDto dto);

	String deleteConfig(Long id);

}
