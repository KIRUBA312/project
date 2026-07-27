package com.example.multiregion_resilience.resilience;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class RegionCircuitBreakerService {

    @Retry(name = "regionApi")
    @CircuitBreaker(
            name = "regionApi",
            fallbackMethod = "fallback"
    )
    @Bulkhead(
            name = "regionApi"
    )
    @RateLimiter(
            name = "regionApi"
    )
    public <T> T execute(
            Supplier<T> supplier) {

        return supplier.get();
    }


    public <T> T fallback(
            Supplier<T> supplier,
            Throwable throwable) {

        throw new RegionResilienceException(
                "Region API unavailable after resilience processing",
                throwable
        );
    }
}