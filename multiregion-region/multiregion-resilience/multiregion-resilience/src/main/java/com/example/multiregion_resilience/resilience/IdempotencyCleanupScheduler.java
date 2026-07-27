package com.example.multiregion_resilience.resilience;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.multiregion_resilience.service.IdempotencyService;

@Component
public class IdempotencyCleanupScheduler {

    private final IdempotencyService
            idempotencyService;

    public IdempotencyCleanupScheduler(
            IdempotencyService idempotencyService) {

        this.idempotencyService =
                idempotencyService;
    }

    @Scheduled(
            fixedDelayString  =  "${idempotency.cleanup-interval-ms:3600000}"
    )
    public void cleanupExpiredRecords() {

        idempotencyService
                .cleanupExpiredRecords();
    }
}