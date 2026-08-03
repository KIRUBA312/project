package com.example.cdc_synchronization_engine.exception;

public class KafkaPublishException extends RuntimeException {

    private final ErrorCode errorCode;

    public KafkaPublishException(ErrorCode errorCode,
                                 String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}