package com.example.cdc_synchronization_engine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponse {

    private Long id;

    private Long userId;

    private String username;

    private Long roleId;

    private String roleName;
}