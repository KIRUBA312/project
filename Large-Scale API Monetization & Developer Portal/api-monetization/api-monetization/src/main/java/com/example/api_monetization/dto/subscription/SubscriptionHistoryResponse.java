package com.example.api_monetization.dto.subscription;

import java.time.LocalDateTime;

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
public class SubscriptionHistoryResponse {

    private Long id;

    private Long subscriptionId;

    private Long previousPlanId;

    private Long newPlanId;

    private Long changedBy;

    private String actionType;

    private String remarks;

    private LocalDateTime changedAt;

}