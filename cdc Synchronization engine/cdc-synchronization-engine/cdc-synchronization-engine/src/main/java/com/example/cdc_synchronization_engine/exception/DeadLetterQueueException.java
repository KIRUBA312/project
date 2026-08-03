package com.example.cdc_synchronization_engine.exception;

public class DeadLetterQueueException extends RuntimeException {

    private final ErrorCode errorCode;

    public DeadLetterQueueException(ErrorCode errorCode,
                                    String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}