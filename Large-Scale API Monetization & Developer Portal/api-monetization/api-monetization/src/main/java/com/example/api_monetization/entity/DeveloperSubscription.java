package com.example.api_monetization.entity;
import java.time.LocalDate;
import java.util.List;

import com.example.api_monetization.enums.BillingCycle;
import com.example.api_monetization.enums.SubscriptionStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "developer_subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperSubscription extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id", nullable = false)
    private DeveloperProfile developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ConsumerApplication application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private SubscriptionPlan plan;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_status", length = 30)
    private SubscriptionStatus subscriptionStatus = SubscriptionStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "billing_cycle", length = 20)
    private BillingCycle billingCycle = BillingCycle.MONTHLY;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "auto_renew")
    private Boolean autoRenew = true;

    @Column(name = "next_billing_date")
    private LocalDate nextBillingDate;

    /*
     * ==========================
     * Relationships
     * ==========================
     */

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubscriptionHistory> subscriptionHistories;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuotaLimit> quotaLimits;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuotaUsage> quotaUsages;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApiUsageLog> apiUsageLogs;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RateLimitLog> rateLimitLogs;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices;
	
}
