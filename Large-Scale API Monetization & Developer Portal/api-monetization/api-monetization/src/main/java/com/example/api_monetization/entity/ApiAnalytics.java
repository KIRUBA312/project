package com.example.api_monetization.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "api_analytics",
       uniqueConstraints = @UniqueConstraint(columnNames = {
               "api_id", "analytics_date"
       }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiAnalytics extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_id", nullable = false)
    private Api api;

    @Column(name = "analytics_date", nullable = false)
    private LocalDate analyticsDate;

    @Column(name = "total_requests")
    private Long totalRequests;

    @Column(name = "successful_requests")
    private Long successfulRequests;

    @Column(name = "failed_requests")
    private Long failedRequests;

    @Column(name = "average_response_time")
    private Long averageResponseTime;

    @Column(name = "total_bandwidth")
    private Long totalBandwidth;

    @Column(name = "total_consumers")
    private Long totalConsumers;
}