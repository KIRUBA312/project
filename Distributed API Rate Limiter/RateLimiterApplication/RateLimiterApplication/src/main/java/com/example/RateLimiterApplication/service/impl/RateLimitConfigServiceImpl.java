package com.example.RateLimiterApplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RateLimiterApplication.dto.RateLimitConfigRequestDto;
import com.example.RateLimiterApplication.dto.RateLimitConfigResponseDto;
import com.example.RateLimiterApplication.entity.RateLimitingConfig;
import com.example.RateLimiterApplication.exception.ResourceNotFoundException;
import com.example.RateLimiterApplication.repository.RateLimitConfigRepository;
import com.example.RateLimiterApplication.service.RateLimitConfigService;

@Service
public class RateLimitConfigServiceImpl 
	implements RateLimitConfigService{
	
	@Autowired
	private RateLimitConfigRepository repository;

	@Override
	public RateLimitConfigResponseDto createConfig(RateLimitConfigRequestDto dto) {
		// TODO Auto-generated method stub
		RateLimitingConfig config = new RateLimitingConfig();
		
		config.setApiName(dto.getApiName());
		config.setEndpoint(dto.getEndpoint());
		config.setRequestLimit(dto.getRequestLimit());
		config.setWindowSeconds(dto.getWindowSeconds());
		config.setPremiumLimit(dto.getPremiumLimit());
		config.setIsActive(dto.getIsActive());
		
		config = repository.save(config);
		return maptoresponse(config);
	}

	@Override
	public List<RateLimitConfigResponseDto> getAllConfig() {
		// TODO Auto-generated method stub
		return repository.findAll().stream().map(this::maptoresponse)
				.collect(Collectors.toList());
	}

	@Override
	public RateLimitConfigResponseDto getConfigById(Long id) {
		// TODO Auto-generated method stub
		RateLimitingConfig config = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Rate Limit Config Not Found"));
		return maptoresponse(config);
	}

	@Override
	public RateLimitConfigResponseDto updateConfig(Long id, 
			RateLimitConfigRequestDto dto) {
		// TODO Auto-generated method stub
		RateLimitingConfig config = repository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Rate Limit Config Not Found"));
		config.setApiName(dto.getApiName());
		config.setEndpoint(dto.getEndpoint());
		config.setRequestLimit(dto.getRequestLimit());
		config.setWindowSeconds(dto.getWindowSeconds());
		config.setPremiumLimit(dto.getPremiumLimit());
		config.setIsActive(dto.getIsActive());
		
		config = repository.save(config);
		return maptoresponse(config);
	}

	@Override
	public String deleteConfig(Long id) {
		// TODO Auto-generated method stub
		RateLimitingConfig config = repository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException(
						"Rate Limit Config Not Found"));
		repository.delete(config);
		return "rate limit config deleted successfully";
	}
	private RateLimitConfigResponseDto maptoresponse(
			RateLimitingConfig config) {
		RateLimitConfigResponseDto dto = new RateLimitConfigResponseDto();
		
		dto.setId(config.getId());
		dto.setApiName(config.getApiName());
		dto.setEndpoint(config.getEndpoint());
		dto.setRequestLimit(config.getRequestLimit());
		dto.setWindowSeconds(config.getWindowSeconds());
		dto.setPremiumLimit(config.getPremiumLimit());
		dto.setIsActive(config.getIsActive());
		
		return dto;
	}
	

}
