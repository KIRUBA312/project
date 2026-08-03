package com.example.cdc_synchronization_engine.exception;

public class DatabaseException extends RuntimeException {

    private final ErrorCode errorCode;

    public DatabaseException(ErrorCode errorCode,
                             String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}