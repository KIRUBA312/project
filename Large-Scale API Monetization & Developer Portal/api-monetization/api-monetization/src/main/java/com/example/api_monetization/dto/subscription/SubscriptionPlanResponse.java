package com.example.api_monetization.dto.subscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.api_monetization.enums.SupportLevel;

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
public class SubscriptionPlanResponse {

    private Long id;

    private String planName;

    private String description;

    private BigDecimal monthlyPrice;

    private BigDecimal yearlyPrice;

    private Long requestLimit;

    private Integer burstLimit;

    private BigDecimal overagePricePer1000;

    private SupportLevel supportLevel;

    private Boolean analyticsEnabled;

    private Boolean customDomainEnabled;

    private Boolean prioritySupport;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}