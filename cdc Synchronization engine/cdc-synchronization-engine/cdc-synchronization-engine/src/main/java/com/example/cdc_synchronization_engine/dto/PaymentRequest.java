package com.example.cdc_synchronization_engine.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @Size(max = 50)
    private String paymentReference;

    @NotNull(message = "Order Id is required")
    private Long orderId;

    @NotNull(message = "Payment amount is required")
    @DecimalMin(value = "0.0")
    private Double paymentAmount;

    @NotBlank(message = "Payment method is required")
    @Size(max = 50)
    private String paymentMethod;

//    @NotBlank(message = "Payment status is required")
//    @Size(max = 30)
//    private String paymentStatus;

}