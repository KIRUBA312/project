package com.example.cdc_synchronization_engine.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiErrorResponse {

    private LocalDateTime timestamp;

    private int status;

    private String errorCode;

    private String message;

    private String path;

    private String requestId;
}