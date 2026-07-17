package com.example.api_monetization.dto.subscription;

import java.time.LocalDate;

import com.example.api_monetization.enums.BillingCycle;

import jakarta.validation.constraints.NotNull;
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
public class DeveloperSubscriptionRequest {

    @NotNull
    private Long developerId;

    @NotNull
    private Long applicationId;

    @NotNull
    private Long planId;

    @NotNull
    private BillingCycle billingCycle;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean autoRenew;

}