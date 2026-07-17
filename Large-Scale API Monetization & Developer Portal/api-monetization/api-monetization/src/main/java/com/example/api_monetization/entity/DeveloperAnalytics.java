package com.example.api_monetization.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "developer_analytics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeveloperAnalytics extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id", nullable = false)
    private DeveloperProfile developer;

    @Column(name = "analytics_date")
    private LocalDate analyticsDate;

    @Column(name = "total_applications")
    private Long totalApplications;

    @Column(name = "total_api_calls")
    private Long totalApiCalls;

    @Column(name = "active_subscriptions")
    private Long activeSubscriptions;

    @Column(name = "monthly_bill", precision = 12, scale = 2)
    private BigDecimal monthlyBill;
}