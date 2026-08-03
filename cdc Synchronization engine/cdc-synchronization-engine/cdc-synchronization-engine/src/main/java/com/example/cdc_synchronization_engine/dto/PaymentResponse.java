package com.example.cdc_synchronization_engine.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long id;

    private String paymentReference;

    private Long orderId;

    private Double paymentAmount;

    private String paymentMethod;

    private String paymentStatus;

    private LocalDateTime createdAt;

}