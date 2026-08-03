package com.example.cdc_synchronization_engine.exception;

public class IdempotencyException extends RuntimeException {

    private final ErrorCode errorCode;

    public IdempotencyException(ErrorCode errorCode,
                                String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}