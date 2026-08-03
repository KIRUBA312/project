package com.example.cdc_synchronization_engine.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long roleId;
}