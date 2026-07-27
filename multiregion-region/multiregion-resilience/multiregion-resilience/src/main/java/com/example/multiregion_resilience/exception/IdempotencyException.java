package com.example.multiregion_resilience.exception;

public class IdempotencyException
        extends RuntimeException {

    private final ErrorCode errorCode;


    public IdempotencyException(
            String message
    ) {
        super(message);
        this.errorCode = ErrorCode.IDEMPOTENCY_CONFLICT;
    }


    public IdempotencyException(
            ErrorCode errorCode,
            String message
    ) {
        super(message);
        this.errorCode = errorCode;
    }


    public ErrorCode getErrorCode() {
        return errorCode;
    }
}