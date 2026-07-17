package com.example.api_monetization.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quota_usage",
       uniqueConstraints = {
           @UniqueConstraint(
               name = "uk_usage_date",
               columnNames = {"subscription_id","usage_date"})
       })
@Getter
@Setter
@NoArgsConstructor
public class QuotaUsage extends BaseEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private DeveloperSubscription subscription;

    @Column(name = "usage_date", nullable = false)
    private LocalDate usageDate;

    @Column(name = "daily_requests")
    private Long dailyRequests = 0L;

    @Column(name = "weekly_requests")
    private Long weeklyRequests = 0L;

    @Column(name = "monthly_requests")
    private Long monthlyRequests = 0L;

    @Column(name = "yearly_requests")
    private Long yearlyRequests = 0L;

    @Column(name = "overage_requests")
    private Long overageRequests = 0L;

    @Column(name = "total_cost", precision = 12, scale = 2)
    private BigDecimal totalCost = BigDecimal.ZERO;

}