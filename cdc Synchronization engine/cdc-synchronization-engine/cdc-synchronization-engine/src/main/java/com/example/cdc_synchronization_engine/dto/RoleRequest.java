package com.example.cdc_synchronization_engine.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {

    @NotBlank
    @Size(max = 50)
    private String roleName;

    @Size(max = 255)
    private String description;
}