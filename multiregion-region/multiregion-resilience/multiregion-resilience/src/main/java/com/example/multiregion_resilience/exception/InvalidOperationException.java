package com.example.multiregion_resilience.exception;

public class InvalidOperationException
        extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidOperationException(
            String message
    ) {
        super(message);
        this.errorCode = ErrorCode.INVALID_OPERATION;
    }

    public InvalidOperationException(
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
