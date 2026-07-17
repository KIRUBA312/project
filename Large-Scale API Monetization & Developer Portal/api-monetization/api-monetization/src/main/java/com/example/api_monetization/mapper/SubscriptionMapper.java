package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.subscription.DeveloperSubscriptionRequest;
import com.example.api_monetization.dto.subscription.DeveloperSubscriptionResponse;
import com.example.api_monetization.dto.subscription.SubscriptionPlanRequest;
import com.example.api_monetization.dto.subscription.SubscriptionPlanResponse;
import com.example.api_monetization.entity.DeveloperSubscription;
import com.example.api_monetization.entity.SubscriptionPlan;

@Mapper(config = MapperConfig.class)
public interface SubscriptionMapper {

	SubscriptionPlan toPlanEntity(SubscriptionPlanRequest dto);
	SubscriptionPlanResponse toPlanResponse(SubscriptionPlan entity);
	
	void updatePlan(SubscriptionPlanRequest request,
			@MappingTarget SubscriptionPlan entity);
	
	//developer subscription
	
	DeveloperSubscription toSubscriptionEntity(DeveloperSubscriptionRequest request);
	DeveloperSubscriptionResponse toSubscriptionResponse(DeveloperSubscription entity);
	
	void updateSubscription(DeveloperSubscriptionRequest request,
			@MappingTarget DeveloperSubscription entity);
	
}
