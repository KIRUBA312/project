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
public class DashboardResponse {

    private Long id;

    private LocalDate summaryDate;

    private Long totalUsers;

    private Long totalDevelopers;

    private Long totalApis;

    private Long totalSubscriptions;

    private Long totalRequests;

    private BigDecimal totalRevenue;

}