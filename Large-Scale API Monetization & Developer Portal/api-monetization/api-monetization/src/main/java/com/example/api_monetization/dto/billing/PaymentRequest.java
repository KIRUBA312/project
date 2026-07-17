package com.example.api_monetization.dto.billing;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.api_monetization.enums.PaymentMethod;
import com.example.api_monetization.enums.PaymentStatus;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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
public class PaymentRequest {

    @NotNull
    private Long invoiceId;

    @NotBlank(message = "Payment reference is required")
    private String paymentReference;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal amount;
    
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;

    private String transactionId;

}