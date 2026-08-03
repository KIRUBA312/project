package com.example.cdc_synchronization_engine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponse {

    private Long id;

    private String username;

    private String email;

    private String role;

    private String message;

}