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
public class QuotaResponse {

    private Long id;

    private Long subscriptionId;

    private Long dailyLimit;

    private Long weeklyLimit;

    private Long monthlyLimit;

    private Long yearlyLimit;

    private Integer concurrentRequests;

    private Integer requestsPerMinute;

    private Integer requestsPerSecond;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}