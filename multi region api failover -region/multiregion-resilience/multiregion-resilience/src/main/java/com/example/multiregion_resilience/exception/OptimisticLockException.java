package com.example.multiregion_resilience.exception;

public class OptimisticLockException
        extends RuntimeException {

    private final ErrorCode errorCode;


    public OptimisticLockException(
            String message
    ) {
        super(message);
        this.errorCode =
                ErrorCode.OPTIMISTIC_LOCK_CONFLICT;
    }


    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
