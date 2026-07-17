package com.example.api_monetization.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quota_limits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuotaLimit extends BaseEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private DeveloperSubscription subscription;

    @Column(name = "daily_limit")
    private Long dailyLimit;

    @Column(name = "weekly_limit")
    private Long weeklyLimit;

    @Column(name = "monthly_limit")
    private Long monthlyLimit;

    @Column(name = "yearly_limit")
    private Long yearlyLimit;

    @Column(name = "concurrent_requests")
    private Integer concurrentRequests;

    @Column(name = "requests_per_minute")
    private Integer requestsPerMinute;

    @Column(name = "requests_per_second")
    private Integer requestsPerSecond;

}