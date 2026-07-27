package com.example.multiregion_resilience.resilience;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;

@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreaker regionCircuitBreaker(
            CircuitBreakerRegistry circuitBreakerRegistry) {

        return circuitBreakerRegistry
                .circuitBreaker("regionApi");
    }

    @Bean
    public Retry regionRetry(
            RetryRegistry retryRegistry) {

        return retryRegistry
                .retry("regionApi");
    }
}