package com.example.cdc_synchronization_engine.exception;

public class KafkaConsumeException extends RuntimeException {

    private final ErrorCode errorCode;

    public KafkaConsumeException(ErrorCode errorCode,
                                 String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}