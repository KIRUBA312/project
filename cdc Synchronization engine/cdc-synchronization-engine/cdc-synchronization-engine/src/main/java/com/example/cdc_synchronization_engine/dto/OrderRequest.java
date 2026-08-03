package com.example.cdc_synchronization_engine.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotNull(message = "Customer Id is required")
    private Long customerId;

    @NotBlank(message = "Order number is required")
    private String orderNumber;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double totalAmount;

    /*
     * Do not ask client for order status.
     * New orders will automatically be PENDING.
     */

    @NotEmpty(message = "At least one order item is required")
    @Valid
    private List<OrderItemRequest> items;
}