package com.example.cdc_synchronization_engine.exception;

public class ElasticsearchSyncException extends RuntimeException {

    private final ErrorCode errorCode;

    public ElasticsearchSyncException(ErrorCode errorCode,
                                      String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}