package com.example.multiregion_resilience.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multiregion_resilience.dto.HealthResponse;
import com.example.multiregion_resilience.service.HealthService;

@RestController
@RequestMapping("/api/health")
public class HealthController {

	private final HealthService healthService;

	public HealthController(HealthService healthService) {
		super();
		this.healthService = healthService;
	}
	
	@GetMapping
	public ResponseEntity<List<HealthResponse>> getAllRegionalHealthStatus(){
		List<HealthResponse> response = healthService.getAllRegionHealth();
		
		return ResponseEntity.ok(response);
	}
	@GetMapping("/{regionCode}")
	public ResponseEntity<HealthResponse> getRegionHealth(
			@PathVariable String regionCode){
		HealthResponse response = healthService
				.getRegionHealth(regionCode);
		return ResponseEntity.ok(response);
	}
	
}
