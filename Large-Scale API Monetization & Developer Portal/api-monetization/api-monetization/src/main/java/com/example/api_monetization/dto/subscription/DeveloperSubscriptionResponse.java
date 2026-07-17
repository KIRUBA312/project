package com.example.api_monetization.dto.subscription;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.api_monetization.enums.BillingCycle;
import com.example.api_monetization.enums.SubscriptionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperSubscriptionResponse {

    private Long id;

    private Long developerId;

    private Long applicationId;

    private Long planId;

    private SubscriptionStatus subscriptionStatus;

    private BillingCycle billingCycle;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean autoRenew;

    private LocalDate nextBillingDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}