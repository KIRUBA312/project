package com.example.api_monetization.exception;

public class ApiRateLimitExceededException extends RuntimeException {

    public ApiRateLimitExceededException(String message) {
        super(message);
    }

}