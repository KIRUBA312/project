package com.example.api_monetization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api_monetization.dto.analytics.ApiAnalyticsResponse;
import com.example.api_monetization.dto.analytics.DashboardResponse;
import com.example.api_monetization.dto.analytics.DeveloperAnalyticsResponse;
import com.example.api_monetization.exception.ResourceNotFoundException;
import com.example.api_monetization.mapper.AnalyticsMapper;
import com.example.api_monetization.mapper.DashboardMapper;
import com.example.api_monetization.repository.ApiAnalyticsRepository;
import com.example.api_monetization.repository.ApiRepository;
import com.example.api_monetization.repository.DashboardSummaryRepository;
import com.example.api_monetization.repository.DeveloperAnalyticsRepository;
import com.example.api_monetization.repository.DeveloperProfileRepository;
import com.example.api_monetization.service.AnalyticsService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService{

	
	private final ApiAnalyticsRepository analyticsRepository;
	
	private final DeveloperAnalyticsRepository developerAnalyticsRepository;
	
	private final ApiRepository apiRepository;
	
	private final DeveloperProfileRepository developerProfileRepository;
	
	private final DashboardSummaryRepository dashboardSummaryRepository;
	
	private final AnalyticsMapper analyticsMapper;
	
	private final DashboardMapper dashboardMapper;
	@Override
	public ApiAnalyticsResponse getApiAnalytics(Long apiId) {
		// TODO Auto-generated method stub 
		if (!apiRepository.existsById(apiId)) {
        throw new ResourceNotFoundException
        (
                "API not found.");
	    }
	
	    return analyticsRepository
	            .findFirstByApiIdOrderByAnalyticsDateDesc(apiId)
	            .map(analyticsMapper::toApiAnalytics)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "API Analytics not found."));
		
	}
	@Override
	public DeveloperAnalyticsResponse getDeveloperAnalytics(
			Long developerId) {
		// TODO Auto-generated method stub
		if (!developerProfileRepository.existsById(developerId)) {
	        throw new ResourceNotFoundException(
	                "Developer not found.");
	    }

	    return developerAnalyticsRepository
	            .findFirstByDeveloperIdOrderByAnalyticsDateDesc(
	                    developerId)
	            .map(analyticsMapper::tDeveloperAnalytics)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Developer Analytics not found."));
	}
	@Override
	public DashboardResponse getDashboardSummary() {
		// TODO Auto-generated method stub
		return dashboardSummaryRepository
				.findFirstByOrderBySummaryDateDesc()
				.map(dashboardMapper::toResponse)
				.orElseThrow(()->new ResourceNotFoundException(
						"Dashboard Summary not found"));
	}
	
}
