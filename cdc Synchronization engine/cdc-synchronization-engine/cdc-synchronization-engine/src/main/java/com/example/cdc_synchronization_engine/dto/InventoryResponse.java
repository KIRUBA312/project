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
public class InventoryResponse {

    private Long id;

    private Long productId;

    private String warehouseName;

    private Integer availableQuantity;

    private Integer reservedQuantity;

    private LocalDateTime lastUpdated;
}