package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;

import com.example.api_monetization.dto.analytics.ApiAnalyticsResponse;
import com.example.api_monetization.dto.analytics.DeveloperAnalyticsResponse;
import com.example.api_monetization.entity.ApiAnalytics;
import com.example.api_monetization.entity.DeveloperAnalytics;

@Mapper(config = MapperConfig.class)
public interface AnalyticsMapper {
	
	ApiAnalyticsResponse toApiAnalytics(ApiAnalytics entity);
	
	DeveloperAnalyticsResponse tDeveloperAnalytics(DeveloperAnalytics entity);
	

}
