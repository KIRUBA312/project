package com.example.multiregion_resilience.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.multiregion_resilience.dto.FailoverResponse;
import com.example.multiregion_resilience.entity.IdempotencyRecord;
import com.example.multiregion_resilience.enums.IdempotencyStatus;
import com.example.multiregion_resilience.exception.ErrorCode;
import com.example.multiregion_resilience.exception.IdempotencyException;
import com.example.multiregion_resilience.repository.IdempotencyRecordRepository;
import com.example.multiregion_resilience.service.IdempotencyService;
import com.example.multiregion_resilience.util.IdempotencyUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class IdempotencyServiceImpl
        implements IdempotencyService {

    private final IdempotencyRecordRepository
            idempotencyRecordRepository;

    private final IdempotencyUtil idempotencyUtil;

    private final ObjectMapper objectMapper;

    private final boolean idempotencyEnabled;

    private final long ttlSeconds;

    public IdempotencyServiceImpl(
            IdempotencyRecordRepository
                    idempotencyRecordRepository,
            IdempotencyUtil idempotencyUtil,
            ObjectMapper objectMapper,
            @Value("${idempotency.enabled:true}")
            boolean idempotencyEnabled,
            @Value("${idempotency.ttl-seconds:86400}")
            long ttlSeconds) {

        this.idempotencyRecordRepository =
                idempotencyRecordRepository;

        this.idempotencyUtil =
                idempotencyUtil;

        this.objectMapper =
                objectMapper;

        this.idempotencyEnabled =
                idempotencyEnabled;

        this.ttlSeconds =
                ttlSeconds;
    }

    @Override
    public IdempotencyResult startProcessing(
            String idempotencyKey,
            String requestHash) {

        if (!idempotencyEnabled) {

            return new IdempotencyResult(
                    false,
                    null
            );
        }

        validateKey(idempotencyKey);

        IdempotencyRecord existingRecord =
                idempotencyRecordRepository
                        .findByIdempotencyKey(
                                idempotencyKey
                        )
                        .orElse(null);

        if (existingRecord != null) {

            if (isExpired(existingRecord)) {

                idempotencyRecordRepository
                        .delete(existingRecord);

                return createProcessingRecord(
                        idempotencyKey,
                        requestHash
                );
            }

            validateRequestHash(
                    existingRecord.getRequestHash(),
                    requestHash
            );

            if (existingRecord.getStatus()
                    == IdempotencyStatus.COMPLETED) {

                FailoverResponse previousResponse =
                        deserializeResponse(
                                existingRecord
                                        .getResponseBody()
                        );

                return new IdempotencyResult(
                        true,
                        previousResponse
                );
            }

            if (existingRecord.getStatus()
                    == IdempotencyStatus.PROCESSING) {

                throw new IdempotencyException(
                        ErrorCode.INVALID_OPERATION,
                        "Request with idempotency key "
                        + idempotencyKey
                        + " is already being processed"
                );
            }

            if (existingRecord.getStatus()
                    == IdempotencyStatus.FAILED) {

                existingRecord.setStatus(
                        IdempotencyStatus.PROCESSING
                );

                existingRecord.setResponseBody(
                        null
                );

                existingRecord.setResponseStatus(
                        null
                );

                existingRecord.setExpiresAt(
                        LocalDateTime.now()
                                .plusSeconds(
                                        ttlSeconds
                                )
                );

                idempotencyRecordRepository
                        .save(existingRecord);

                return new IdempotencyResult(
                        false,
                        null
                );
            }
        }

        return createProcessingRecord(
                idempotencyKey,
                requestHash
        );
    }

    @Override
    public void complete(
            String idempotencyKey,
            String requestHash,
            FailoverResponse response,
            int responseStatus) {

        if (!idempotencyEnabled) {
            return;
        }

        IdempotencyRecord record =
                idempotencyRecordRepository
                        .findByIdempotencyKey(
                                idempotencyKey
                        )
                        .orElseThrow(
                                () -> new IdempotencyException(
                                        ErrorCode.INVALID_OPERATION,
                                        "Idempotency record not found"
                                )
                        );

        validateRequestHash(
                record.getRequestHash(),
                requestHash
        );

        record.setStatus(
                IdempotencyStatus.COMPLETED
        );

        record.setResponseBody(
                serializeResponse(response)
        );

        record.setResponseStatus(
                responseStatus
        );

        record.setExpiresAt(
                LocalDateTime.now()
                        .plusSeconds(ttlSeconds)
        );

        idempotencyRecordRepository
                .save(record);
    }

    @Override
    public void markFailed(
            String idempotencyKey,
            String requestHash) {

        if (!idempotencyEnabled) {
            return;
        }

        idempotencyRecordRepository
                .findByIdempotencyKey(
                        idempotencyKey
                )
                .ifPresent(record -> {

                    validateRequestHash(
                            record.getRequestHash(),
                            requestHash
                    );

                    record.setStatus(
                            IdempotencyStatus.FAILED
                    );

                    record.setExpiresAt(
                            LocalDateTime.now()
                                    .plusSeconds(
                                            ttlSeconds
                                    )
                    );

                    idempotencyRecordRepository
                            .save(record);
                });
    }

    @Override
    public void validateRequestHash(
            String existingHash,
            String incomingHash) {

        if (existingHash == null
                || incomingHash == null
                || !existingHash.equals(
                        incomingHash
                )) {

            throw new IdempotencyException(
                    ErrorCode.INVALID_OPERATION,
                    "The same Idempotency-Key cannot "
                    + "be used with a different request"
            );
        }
    }

    private IdempotencyResult
    createProcessingRecord(
            String idempotencyKey,
            String requestHash) {

        IdempotencyRecord record =
                new IdempotencyRecord();

        record.setIdempotencyKey(
                idempotencyKey
        );

        record.setRequestHash(
                requestHash
        );

        record.setStatus(
                IdempotencyStatus.PROCESSING
        );

        record.setExpiresAt(
                LocalDateTime.now()
                        .plusSeconds(
                                ttlSeconds
                        )
        );
        idempotencyRecordRepository
                .save(record);

        return new IdempotencyResult(
                false,
                null
        );
    }
    
    @Override
    @Transactional
    public void cleanupExpiredRecords() {

        idempotencyRecordRepository
                .deleteByExpiresAtBefore(
                        LocalDateTime.now()
                );
    }

    private boolean isExpired(
            IdempotencyRecord record) {

        return record.getExpiresAt() != null
                && record.getExpiresAt()
                        .isBefore( LocalDateTime.now());
    }

    private String serializeResponse(
            FailoverResponse response) {

        try {

            return objectMapper.writeValueAsString(
                    response
            );
        } catch (JsonProcessingException exception) {
            throw new IdempotencyException(
                    ErrorCode.INVALID_OPERATION,
                    "Unable to serialize idempotency response"
            );
        }
    }

    private FailoverResponse deserializeResponse(
            String responseBody) {

        try {

            return objectMapper.readValue(
                    responseBody,
                    FailoverResponse.class
            );
        } catch (JsonProcessingException exception) {
            throw new IdempotencyException(
                    ErrorCode.INVALID_OPERATION,
                    "Unable to deserialize stored idempotency response"
            );
        }
    }

    private void validateKey(
            String idempotencyKey) {

        if (idempotencyKey == null
                || idempotencyKey.isBlank()) {

            throw new IdempotencyException(
                    ErrorCode.INVALID_OPERATION,
                    "Idempotency-Key header is required"
            );
        }

        if (idempotencyKey.length() > 255) {

            throw new IdempotencyException(
                    ErrorCode.INVALID_OPERATION,
                    "Idempotency-Key cannot exceed 255 characters"
            );
        }
    }
}