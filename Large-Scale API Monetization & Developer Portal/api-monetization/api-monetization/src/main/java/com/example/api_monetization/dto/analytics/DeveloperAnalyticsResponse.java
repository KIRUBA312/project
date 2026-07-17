package com.example.api_monetization.dto.analytics;

import java.math.BigDecimal;
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
public class DeveloperAnalyticsResponse {

    private Long id;

    private Long developerId;

    private LocalDate analyticsDate;

    private Long totalApplications;

    private Long totalApiCalls;

    private Long activeSubscriptions;

    private BigDecimal monthlyBill;

}