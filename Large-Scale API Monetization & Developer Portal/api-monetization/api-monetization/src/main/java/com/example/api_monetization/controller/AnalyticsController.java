package com.example.api_monetization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_monetization.dto.analytics.ApiAnalyticsResponse;
import com.example.api_monetization.dto.analytics.DashboardResponse;
import com.example.api_monetization.dto.analytics.DeveloperAnalyticsResponse;
import com.example.api_monetization.service.AnalyticsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

	@Autowired
	private AnalyticsService analyticsService;
	
	@GetMapping("/apis/{apiId}")
	public ResponseEntity<ApiAnalyticsResponse> getApiAnalytics(
			@PathVariable Long apiId){
		
		return ResponseEntity.ok(analyticsService
				.getApiAnalytics(apiId));
	}
	@GetMapping("/developers/{developerId}")
	public ResponseEntity<DeveloperAnalyticsResponse> getDeveloperAnalytics(
			@PathVariable Long developerId){
		return ResponseEntity.ok(analyticsService.getDeveloperAnalytics(
				developerId));
	}
	@GetMapping("/dashboard")
	public ResponseEntity<DashboardResponse> getDashboardSummary(){
		
		return ResponseEntity.ok(analyticsService.getDashboardSummary());
	}
	
}
