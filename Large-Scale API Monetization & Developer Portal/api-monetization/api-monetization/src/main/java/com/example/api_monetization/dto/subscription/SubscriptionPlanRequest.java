package com.example.api_monetization.dto.subscription;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class SubscriptionPlanRequest {

    @NotBlank(message = "Plan name is required")
    @Size(max = 100)
    private String planName;

    private String description;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal monthlyPrice;

    private BigDecimal yearlyPrice;

    @NotNull
    @Min(1)
    private Long requestLimit;

    private Integer burstLimit;

    private BigDecimal overagePricePer1000;

    private String supportLevel;

    private Boolean analyticsEnabled;

    private Boolean customDomainEnabled;

    private Boolean prioritySupport;

    private Boolean active;

}