package com.example.api_monetization.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rate_limit_logs")
@Getter
@Setter
@NoArgsConstructor
public class RateLimitLog extends BaseEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private DeveloperSubscription subscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_id", nullable = false)
    private Api api;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ConsumerApplication application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    @Column(name = "request_time")
    private LocalDateTime requestTime;

    @Column(name = "requests_used")
    private Long requestsUsed;

    @Column(name = "remaining_requests")
    private Long remainingRequests;

    @Column(name = "quota_limit")
    private Long quotaLimit;

    @Column(name = "throttled")
    private Boolean throttled = false;

    @Column(name = "reason", length = 255)
    private String reason;

}