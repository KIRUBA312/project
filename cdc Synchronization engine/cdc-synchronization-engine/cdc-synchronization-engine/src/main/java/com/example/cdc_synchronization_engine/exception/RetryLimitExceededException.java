package com.example.cdc_synchronization_engine.exception;

public class RetryLimitExceededException extends RuntimeException {

    private final ErrorCode errorCode;

    public RetryLimitExceededException(ErrorCode errorCode,
                                       String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}