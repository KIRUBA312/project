package com.example.multiregion_resilience.service;

import com.example.multiregion_resilience.dto.FailoverResponse;

public interface IdempotencyService {

    IdempotencyResult startProcessing(
            String idempotencyKey,
            String requestHash
    );

    void complete(
            String idempotencyKey,
            String requestHash,
            FailoverResponse response,
            int responseStatus
    );

    void markFailed(
            String idempotencyKey,
            String requestHash
    );

    void validateRequestHash(
            String existingHash,
            String incomingHash
    );

    record IdempotencyResult(
            boolean alreadyCompleted,
            FailoverResponse previousResponse
    ) {
    }

	void cleanupExpiredRecords();
}