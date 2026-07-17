package com.example.api_monetization.entity;
import java.math.BigDecimal;
import java.util.List;

import com.example.api_monetization.enums.SupportLevel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subscription_plans")
@Getter
@Setter
@NoArgsConstructor
public class SubscriptionPlan extends BaseEntity{
	
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_name", nullable = false, unique = true, length = 100)
    private String planName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "monthly_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal monthlyPrice;

    @Column(name = "yearly_price", precision = 12, scale = 2)
    private BigDecimal yearlyPrice;

    @Column(name = "request_limit", nullable = false)
    private Long requestLimit;

    @Column(name = "burst_limit")
    private Integer burstLimit = 100;

    @Column(name = "overage_price_per_1000", precision = 10, scale = 2)
    private BigDecimal overagePricePer1000;

    @Enumerated(EnumType.STRING)
    @Column(name = "support_level", length = 50)
    private SupportLevel supportLevel;

    @Column(name = "analytics_enabled")
    private Boolean analyticsEnabled = true;

    @Column(name = "custom_domain_enabled")
    private Boolean customDomainEnabled = false;

    @Column(name = "priority_support")
    private Boolean prioritySupport = false;

    @Column(name = "active")
    private Boolean active = true;

    /*
     * Relationships
     */

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<DeveloperSubscription> developerSubscriptions;

    @OneToMany(mappedBy = "previousPlan", cascade = CascadeType.ALL)
    private List<SubscriptionHistory> previousPlanHistory;

    @OneToMany(mappedBy = "newPlan", cascade = CascadeType.ALL)
    private List<SubscriptionHistory> newPlanHistory;
}
