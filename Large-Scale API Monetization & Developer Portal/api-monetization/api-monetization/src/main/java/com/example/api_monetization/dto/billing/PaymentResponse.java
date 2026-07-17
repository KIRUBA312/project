package com.example.api_monetization.dto.billing;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.api_monetization.enums.PaymentMethod;
import com.example.api_monetization.enums.PaymentStatus;

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
public class PaymentResponse {

    private Long id;

    private Long invoiceId;

    private String paymentReference;

    private PaymentMethod paymentMethod;

    private BigDecimal amount;

    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;

    private String transactionId;

    private LocalDateTime createdAt;

}