package com.example.cdc_synchronization_engine.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private Long id;
    private String customerCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String customerStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}