package com.example.cdc_synchronization_engine.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {

    @NotNull
    private Long productId;

    @NotBlank
    @Size(max = 100)
    private String warehouseName;

    @NotNull
    @Min(0)
    private Integer availableQuantity;

    @NotNull
    @Min(0)
    private Integer reservedQuantity;
}