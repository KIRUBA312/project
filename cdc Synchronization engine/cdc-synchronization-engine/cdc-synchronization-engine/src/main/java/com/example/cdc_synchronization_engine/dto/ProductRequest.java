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
public class ProductRequest {

    @Size(max = 30)
    private String productCode;

    @NotBlank(message = "Product name is required")
    @Size(max = 150)
    private String productName;

    @Size(max = 500)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0")
    private Double price;

    @Size(max = 100)
    private String category;

    @NotNull
    private Boolean active;
}