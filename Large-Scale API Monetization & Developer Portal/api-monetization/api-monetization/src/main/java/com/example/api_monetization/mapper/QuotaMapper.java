package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.api_monetization.dto.subscription.QuotaResponse;
import com.example.api_monetization.entity.QuotaUsage;

@Mapper(config = MapperConfig.class)
public interface QuotaMapper {

    @Mapping(source = "subscription.id", target = "subscriptionId")
    QuotaResponse toResponse(QuotaUsage entity);

}