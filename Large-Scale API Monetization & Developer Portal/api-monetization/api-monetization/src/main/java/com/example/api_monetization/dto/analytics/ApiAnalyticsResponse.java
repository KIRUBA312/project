package com.example.api_monetization.dto.analytics;

import java.time.LocalDate;

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
public class ApiAnalyticsResponse {

    private Long id;

    private Long apiId;

    private LocalDate analyticsDate;

    private Long totalRequests;

    private Long successfulRequests;

    private Long failedRequests;

    private Long averageResponseTime;

    private Long totalBandwidth;

    private Long totalConsumers;

}