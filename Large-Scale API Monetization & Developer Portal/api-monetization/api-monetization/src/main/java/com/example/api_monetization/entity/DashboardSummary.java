package com.example.api_monetization.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dashboard_summary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardSummary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "summary_date", unique = true)
    private LocalDate summaryDate;

    @Column(name = "total_users")
    private Long totalUsers;

    @Column(name = "total_developers")
    private Long totalDevelopers;

    @Column(name = "total_apis")
    private Long totalApis;

    @Column(name = "total_subscriptions")
    private Long totalSubscriptions;

    @Column(name = "total_requests")
    private Long totalRequests;

    @Column(name = "total_revenue", precision = 15, scale = 2)
    private BigDecimal totalRevenue;
}