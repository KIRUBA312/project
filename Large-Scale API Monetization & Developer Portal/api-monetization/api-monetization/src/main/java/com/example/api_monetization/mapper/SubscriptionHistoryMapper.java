package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.api_monetization.dto.subscription.SubscriptionHistoryResponse;
import com.example.api_monetization.entity.SubscriptionHistory;

@Mapper(config = MapperConfig.class)
public interface SubscriptionHistoryMapper {

    @Mapping(source = "subscription.id", target = "subscriptionId")
    @Mapping(source = "previousPlan.id", target = "previousPlanId")
    @Mapping(source = "newPlan.id", target = "newPlanId")
    @Mapping(source = "changedBy.id", target = "changedBy")
    SubscriptionHistoryResponse toResponse(SubscriptionHistory entity);

}