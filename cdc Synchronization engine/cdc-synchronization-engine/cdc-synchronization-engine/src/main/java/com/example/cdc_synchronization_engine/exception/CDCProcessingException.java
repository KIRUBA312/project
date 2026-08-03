package com.example.cdc_synchronization_engine.exception;

public class CDCProcessingException extends RuntimeException {

    private final ErrorCode errorCode;

    public CDCProcessingException(ErrorCode errorCode,
                                  String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}