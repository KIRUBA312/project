package com.example.api_monetization.service;

import java.util.List;

import com.example.api_monetization.dto.subscription.DeveloperSubscriptionRequest;
import com.example.api_monetization.dto.subscription.DeveloperSubscriptionResponse;
import com.example.api_monetization.dto.subscription.QuotaResponse;
import com.example.api_monetization.dto.subscription.SubscriptionHistoryResponse;
import com.example.api_monetization.dto.subscription.SubscriptionPlanRequest;
import com.example.api_monetization.dto.subscription.SubscriptionPlanResponse;

public interface SubscriptionService {

    SubscriptionPlanResponse createPlan(SubscriptionPlanRequest request);

    SubscriptionPlanResponse updatePlan(Long planId,
                                        SubscriptionPlanRequest request);

    SubscriptionPlanResponse getPlan(Long planId);

   
    List<SubscriptionPlanResponse> getPlans();

    DeveloperSubscriptionResponse subscribe(
            DeveloperSubscriptionRequest request);

    DeveloperSubscriptionResponse updateSubscription(
            Long subscriptionId,
            DeveloperSubscriptionRequest request);

    DeveloperSubscriptionResponse getSubscription(Long subscriptionId);

    List<DeveloperSubscriptionResponse> getDeveloperSubscriptions(
            Long developerId);

   
    List<SubscriptionHistoryResponse> getHistory(
            Long subscriptionId);

    
    QuotaResponse getQuotaUsage(Long subscriptionId);

}