package com.example.cdc_synchronization_engine.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;

    private Long customerId;

    private String orderNumber;

    private Double totalAmount;

    private String orderStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private List<OrderItemResponse> items;


}