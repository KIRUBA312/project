package com.example.multiregion_resilience.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multiregion_resilience.dto.CacheSyncRequest;
import com.example.multiregion_resilience.service.CacheService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cache")
@Validated
public class CacheController {

	private final CacheService cacheService;

	public CacheController(CacheService cacheService) {
		super();
		this.cacheService = cacheService;
	}
	@PostMapping("/sync")
	public ResponseEntity<Void> synchronizeCache(@Valid 
			@RequestBody CacheSyncRequest request){
		cacheService.synchronizeCache(request);
		return ResponseEntity.noContent().build();
	}
	
}
