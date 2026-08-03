package com.example.cdc_synchronization_engine.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private Long id;

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private Double unitPrice;

    private Double totalPrice;

    private LocalDateTime createdAt;

}