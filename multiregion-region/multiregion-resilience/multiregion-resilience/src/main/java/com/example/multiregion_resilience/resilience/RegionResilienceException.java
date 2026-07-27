package com.example.multiregion_resilience.resilience;

public class RegionResilienceException
        extends RuntimeException {

    public RegionResilienceException(
            String message) {

        super(message);
    }


    public RegionResilienceException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}