package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;

import com.example.api_monetization.dto.analytics.DashboardResponse;
import com.example.api_monetization.entity.DashboardSummary;

@Mapper(config = MapperConfig.class)
public interface DashboardMapper {

    DashboardResponse toResponse(DashboardSummary entity);

}