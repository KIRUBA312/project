package com.example.api_monetization.service;

import com.example.api_monetization.dto.analytics.ApiAnalyticsResponse;
import com.example.api_monetization.dto.analytics.DashboardResponse;
import com.example.api_monetization.dto.analytics.DeveloperAnalyticsResponse;

public interface AnalyticsService {

	ApiAnalyticsResponse getApiAnalytics(Long apiId);

	DeveloperAnalyticsResponse getDeveloperAnalytics(Long developerId);

	DashboardResponse getDashboardSummary();

}
