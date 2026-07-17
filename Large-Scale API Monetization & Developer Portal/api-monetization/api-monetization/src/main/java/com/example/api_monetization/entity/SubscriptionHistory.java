package com.example.api_monetization.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

import com.example.api_monetization.enums.ActionType;
import com.example.api_monetization.enums.SubscriptionStatus;


@Entity
@Table(name = "subscription_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionHistory extends BaseEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private DeveloperSubscription subscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_plan")
    private SubscriptionPlan previousPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_plan", nullable = false)
    private SubscriptionPlan newPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by")
    private User changedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", length = 30)
    private ActionType actionType;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "changed_at")
    private LocalDateTime changedAt;
}
