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
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private Boolean enabled;

    private List<String> roles;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}