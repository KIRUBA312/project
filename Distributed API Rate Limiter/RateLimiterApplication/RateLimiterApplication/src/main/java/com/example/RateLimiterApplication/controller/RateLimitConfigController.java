package com.example.RateLimiterApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RateLimiterApplication.dto.RateLimitConfigRequestDto;
import com.example.RateLimiterApplication.dto.RateLimitConfigResponseDto;
import com.example.RateLimiterApplication.service.RateLimitConfigService;


@RestController
@RequestMapping("/api/rate-limit/config")
public class RateLimitConfigController {

	@Autowired
	private RateLimitConfigService service;
	
	@PostMapping
	public ResponseEntity<RateLimitConfigResponseDto> createConfig(
			@RequestBody RateLimitConfigRequestDto dto){
		return ResponseEntity.ok(service.createConfig(dto));
	}
	@GetMapping
	public ResponseEntity<List<RateLimitConfigResponseDto>> getAllConfig(){
		return ResponseEntity.ok(
				service.getAllConfig());
	}
	@GetMapping("/{id}")
	public ResponseEntity<RateLimitConfigResponseDto> getConfigById(
			@PathVariable Long id){
		return ResponseEntity.ok(service.getConfigById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<RateLimitConfigResponseDto> updateConfig(
			@PathVariable Long id,
			@RequestBody RateLimitConfigRequestDto dto){
		return ResponseEntity.ok(service.updateConfig(id,dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteConfig(@PathVariable Long id){
		return ResponseEntity.ok(service.deleteConfig(id));
	}
	
}
