package com.example.multiregion_resilience.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.multiregion_resilience.dto.FailoverRequest;
import com.example.multiregion_resilience.dto.FailoverResponse;
import com.example.multiregion_resilience.dto.PageResponse;
import com.example.multiregion_resilience.dto.RegionResponse;
import com.example.multiregion_resilience.entity.FailoverEvent;
import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.enums.FailoverType;
import com.example.multiregion_resilience.enums.RegionStatus;
import com.example.multiregion_resilience.exception.ErrorCode;
import com.example.multiregion_resilience.exception.InvalidOperationException;
import com.example.multiregion_resilience.exception.ResourceNotFoundException;
import com.example.multiregion_resilience.mapper.FailoverEventMapper;
import com.example.multiregion_resilience.repository.FailoverEventRepository;
import com.example.multiregion_resilience.service.ActiveRegionService;
import com.example.multiregion_resilience.service.FailoverService;
import com.example.multiregion_resilience.service.IdempotencyService;
import com.example.multiregion_resilience.service.RegionService;
import com.example.multiregion_resilience.util.IdempotencyUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FailoverServiceImpl implements FailoverService {

    private final FailoverEventRepository failoverEventRepository;

    private final IdempotencyService idempotencyService;

    private final RegionService regionService;

    private final ActiveRegionService activeRegionService;

    private final IdempotencyUtil idempotencyUtil;

    private final FailoverEventMapper failoverEventMapper;

    private final ObjectMapper objectMapper;

    private final int maxPageSize;


    public FailoverServiceImpl(
            FailoverEventRepository failoverEventRepository,
            RegionService regionService,
            ActiveRegionService activeRegionService,
            FailoverEventMapper failoverEventMapper,
            IdempotencyService idempotencyService,
            IdempotencyUtil idempotencyUtil,
            ObjectMapper objectMapper,
            @Value("${api.max-page-size:100}")
            int maxPageSize) {

        this.failoverEventRepository =
                failoverEventRepository;

        this.regionService =
                regionService;

        this.activeRegionService =
                activeRegionService;

        this.failoverEventMapper =
                failoverEventMapper;

        this.idempotencyService =
                idempotencyService;

        this.idempotencyUtil =
                idempotencyUtil;

        this.objectMapper =
                objectMapper;

        this.maxPageSize =
                maxPageSize;
    }


    // =========================================================
    // MANUAL FAILOVER
    // =========================================================

    @Override
    public FailoverResponse performFailover(
            FailoverRequest request,
            String idempotencyKey) {

        validateFailoverRequest(request);

        String requestHash =
                generateRequestHash(request);

        IdempotencyService.IdempotencyResult
                idempotencyResult =
                idempotencyService.startProcessing(
                        idempotencyKey,
                        requestHash
                );

        if (idempotencyResult.alreadyCompleted()) {

            return idempotencyResult.previousResponse();
        }

        try {

            String sourceRegion =
                    normalizeRegionCode(
                            request.getSourceRegion()
                    );

            String targetRegion =
                    normalizeRegionCode(
                            request.getTargetRegion()
                    );


            validateDifferentRegions(
                    sourceRegion,
                    targetRegion
            );


            RegionResponse source =
                    regionService.getRegionByCode(
                            sourceRegion
                    );

            RegionResponse target =
                    regionService.getRegionByCode(
                            targetRegion
                    );


            if (source == null) {

                throw new ResourceNotFoundException(
                        ErrorCode.REGION_NOT_FOUND,
                        "Source region not found: "
                                + sourceRegion
                );
            }


            validateFailoverTargetRegion(
                    target
            );


            /*
             * IMPORTANT:
             *
             * Actually switch the active region.
             */
            activeRegionService.switchActiveRegion(
                    regionService.getRegionEntityByCode(
                            sourceRegion
                    ),
                    regionService.getRegionEntityByCode(
                            targetRegion
                    )
            );


            FailoverEvent failoverEvent =
                    new FailoverEvent();


            failoverEvent.setSourceRegion(
                    source.getRegionCode()
            );

            failoverEvent.setTargetRegion(
                    target.getRegionCode()
            );

            failoverEvent.setFailoverType(
                    FailoverType.MANUAL
            );

            failoverEvent.setReason(
                    request.getReason().trim()
            );

            failoverEvent.setTriggeredBy(
                    "SYSTEM"
            );


            FailoverEvent savedEvent =
                    failoverEventRepository.save(
                            failoverEvent
                    );


            FailoverResponse response =
                    mapToResponse(
                            savedEvent
                    );


            idempotencyService.complete(
                    idempotencyKey,
                    requestHash,
                    response,
                    201
            );


            return response;


        } catch (Exception exception) {

            idempotencyService.markFailed(
                    idempotencyKey,
                    requestHash
            );

            throw exception;
        }
    }


    // =========================================================
    // MANUAL FAILBACK
    // =========================================================

    @Override
    public FailoverResponse performFailback(
            FailoverRequest request,
            String idempotencyKey) {

        validateFailoverRequest(request);

        String requestHash =
                generateRequestHash(request);

        IdempotencyService.IdempotencyResult
                idempotencyResult =
                idempotencyService.startProcessing(
                        idempotencyKey,
                        requestHash
                );

        if (idempotencyResult.alreadyCompleted()) {

            return idempotencyResult.previousResponse();
        }

        try {

            String originalRegionCode =
                    normalizeRegionCode(
                            request.getSourceRegion()
                    );

            String currentActiveRegionCode =
                    normalizeRegionCode(
                            request.getTargetRegion()
                    );


            validateDifferentRegions(
                    originalRegionCode,
                    currentActiveRegionCode
            );


            RegionResponse originalRegion =
                    regionService.getRegionByCode(
                            originalRegionCode
                    );


            RegionResponse currentActiveRegion =
                    regionService.getRegionByCode(
                            currentActiveRegionCode
                    );


            if (originalRegion == null) {

                throw new ResourceNotFoundException(
                        ErrorCode.REGION_NOT_FOUND,
                        "Failback target region not found: "
                                + originalRegionCode
                );
            }


            if (currentActiveRegion == null) {

                throw new ResourceNotFoundException(
                        ErrorCode.REGION_NOT_FOUND,
                        "Current active region not found: "
                                + currentActiveRegionCode
                );
            }


            /*
             * IMPORTANT:
             *
             * DO NOT CALL:
             *
             * validateTargetRegion(originalRegion);
             *
             * because ASIA can be FAILED before failback.
             */


            if (!Boolean.TRUE.equals(
                    originalRegion.getEnabled()
            )) {

                throw new InvalidOperationException(
                        ErrorCode.INVALID_OPERATION,
                        "Failback target region is disabled: "
                                + originalRegionCode
                );
            }


            /*
             * Get actual Region entities.
             */
            Region originalRegionEntity =
                    regionService.getRegionEntityByCode(
                            originalRegionCode
                    );


            Region currentActiveRegionEntity =
                    regionService.getRegionEntityByCode(
                            currentActiveRegionCode
                    );


            /*
             * Perform actual failback.
             *
             * Current active region:
             * ACTIVE -> STANDBY
             *
             * Original region:
             * FAILED -> ACTIVE
             */
            activeRegionService.failbackToRegion(
                    originalRegionEntity
            );


            /*
             * Create failback audit/event record.
             */
            FailoverEvent failbackEvent =
                    new FailoverEvent();


            failbackEvent.setSourceRegion(
                    currentActiveRegionEntity
                            .getRegionCode()
            );


            failbackEvent.setTargetRegion(
                    originalRegionEntity
                            .getRegionCode()
            );


            failbackEvent.setFailoverType(
                    FailoverType.MANUAL
            );


            failbackEvent.setReason(
                    request.getReason()
                            .trim()
            );


            failbackEvent.setTriggeredBy(
                    "SYSTEM"
            );


            FailoverEvent savedEvent =
                    failoverEventRepository.save(
                            failbackEvent
                    );


            FailoverResponse response =
                    mapToResponse(
                            savedEvent
                    );


            /*
             * Complete idempotency processing.
             */
            idempotencyService.complete(
                    idempotencyKey,
                    requestHash,
                    response,
                    201
            );


            return response;


        } catch (Exception exception) {

            idempotencyService.markFailed(
                    idempotencyKey,
                    requestHash
            );

            throw exception;
        }
    }
    @Override
    public FailoverResponse getFailoverById(
            Long id) {

        if (id == null) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failover event ID cannot be null"
            );
        }


        FailoverEvent failoverEvent =
                failoverEventRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        ErrorCode.FAILOVER_EVENT_NOT_FOUND,
                                        "Failover event not found with id: "
                                                + id
                                )
                        );


        return mapToResponse(
                failoverEvent
        );
    }


    // =========================================================
    // GET ALL
    // =========================================================

    @Override
    public PageResponse<FailoverResponse>
    getAllFailovers(
            int page,
            int size) {

        validatePagination(
                page,
                size
        );


        PageRequest pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(
                                Sort.Direction.DESC,
                                "createdAt"
                        )
                );


        Page<FailoverEvent> result =
                failoverEventRepository
                        .findAllByOrderByCreatedAtDesc(
                                pageable
                        );


        return buildPageResponse(
                result
        );
    }


    // =========================================================
    // GET BY SOURCE REGION
    // =========================================================

    @Override
    public PageResponse<FailoverResponse>
    getFailoversBySourceRegion(
            String sourceRegion,
            int page,
            int size) {


        validateRegionCode(
                sourceRegion
        );


        validatePagination(
                page,
                size
        );


        String normalizedRegion =
                normalizeRegionCode(
                        sourceRegion
                );


        PageRequest pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(
                                Sort.Direction.DESC,
                                "createdAt"
                        )
                );


        Page<FailoverEvent> result =
                failoverEventRepository
                        .findBySourceRegion(
                                normalizedRegion,
                                pageable
                        );


        return buildPageResponse(
                result
        );
    }


    // =========================================================
    // GET BY TARGET REGION
    // =========================================================

    @Override
    public PageResponse<FailoverResponse>
    getFailoversByTargetRegion(
            String targetRegion,
            int page,
            int size) {


        validateRegionCode(
                targetRegion
        );


        validatePagination(
                page,
                size
        );


        String normalizedRegion =
                normalizeRegionCode(
                        targetRegion
                );


        PageRequest pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(
                                Sort.Direction.DESC,
                                "createdAt"
                        )
                );


        Page<FailoverEvent> result =
                failoverEventRepository
                        .findByTargetRegion(
                                normalizedRegion,
                                pageable
                        );


        return buildPageResponse(
                result
        );
    }


    // =========================================================
    // FAILOVER TARGET VALIDATION
    // =========================================================

    private void validateFailoverTargetRegion(
            RegionResponse targetRegion) {

        if (targetRegion == null) {

            throw new ResourceNotFoundException(
                    ErrorCode.REGION_NOT_FOUND,
                    "Target region not found"
            );
        }


        if (!Boolean.TRUE.equals(
                targetRegion.getEnabled()
        )) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Target region is disabled"
            );
        }


        /*
         * Manual failover target must be:
         *
         * ACTIVE
         * or
         * STANDBY
         *
         * It must NOT be FAILED.
         */

        if (targetRegion.getStatus()
                != RegionStatus.ACTIVE
                && targetRegion.getStatus()
                != RegionStatus.STANDBY) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Target region must be ACTIVE or STANDBY: "
                            + targetRegion.getRegionCode()
            );
        }
    }


    // =========================================================
    // FAILBACK TARGET VALIDATION
    // =========================================================

    private void validateFailbackTargetRegion(
            RegionResponse targetRegion) {

        if (targetRegion == null) {

            throw new ResourceNotFoundException(
                    ErrorCode.REGION_NOT_FOUND,
                    "Failback target region not found"
            );
        }


        if (!Boolean.TRUE.equals(
                targetRegion.getEnabled()
        )) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failback target region is disabled: "
                            + targetRegion.getRegionCode()
            );
        }


        /*
         * IMPORTANT:
         *
         * A failed region is a valid failback target.
         *
         * Therefore accept:
         *
         * FAILED
         * STANDBY
         * ACTIVE
         *
         * ACTIVE is allowed so that the method remains
         * idempotent when the region is already active.
         */

        if (targetRegion.getStatus()
                != RegionStatus.FAILED
                && targetRegion.getStatus()
                != RegionStatus.STANDBY
                && targetRegion.getStatus()
                != RegionStatus.ACTIVE) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failback target region must be "
                    + "FAILED, ACTIVE or STANDBY: "
                    + targetRegion.getRegionCode()
            );
        }
    }


    // =========================================================
    // REQUEST HASH
    // =========================================================

    private String generateRequestHash(
            FailoverRequest request) {

        try {

            String json =
                    objectMapper.writeValueAsString(
                            request
                    );


            return idempotencyUtil
                    .generateRequestHash(
                            json
                    );


        } catch (JsonProcessingException exception) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Unable to generate request hash"
            );
        }
    }


    // =========================================================
    // PAGE RESPONSE
    // =========================================================

    private PageResponse<FailoverResponse>
    buildPageResponse(
            Page<FailoverEvent> page) {


        PageResponse<FailoverResponse>
                response =
                new PageResponse<>();


        response.setContent(
                page.getContent()
                        .stream()
                        .map(
                                failoverEventMapper
                                        ::toResponse
                        )
                        .toList()
        );


        response.setPage(
                page.getNumber()
        );


        response.setSize(
                page.getSize()
        );


        response.setTotalElements(
                page.getTotalElements()
        );


        response.setTotalPages(
                page.getTotalPages()
        );


        response.setFirst(
                page.isFirst()
        );


        response.setLast(
                page.isLast()
        );


        return response;
    }


    // =========================================================
    // PAGINATION VALIDATION
    // =========================================================

    private void validatePagination(
            int page,
            int size) {


        if (page < 0) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Page number cannot be negative"
            );
        }


        if (size <= 0) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Page size must be greater than zero"
            );
        }


        if (size > maxPageSize) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Page size cannot exceed "
                            + maxPageSize
            );
        }
    }


    // =========================================================
    // REGION CODE VALIDATION
    // =========================================================

    private void validateRegionCode(
            String regionCode) {


        if (regionCode == null
                || regionCode.isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Region code cannot be empty"
            );
        }
    }


    // =========================================================
    // FAILOVER REQUEST VALIDATION
    // =========================================================

    private void validateFailoverRequest(
            FailoverRequest request) {


        if (request == null) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failover request cannot be null"
            );
        }


        if (request.getSourceRegion() == null
                || request.getSourceRegion()
                        .isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Source region cannot be empty"
            );
        }


        if (request.getTargetRegion() == null
                || request.getTargetRegion()
                        .isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Target region cannot be empty"
            );
        }


        if (request.getReason() == null
                || request.getReason()
                        .isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failover reason cannot be empty"
            );
        }
    }


    // =========================================================
    // SAME REGION VALIDATION
    // =========================================================

    private void validateDifferentRegions(
            String sourceRegion,
            String targetRegion) {


        if (sourceRegion.equalsIgnoreCase(
                targetRegion
        )) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Source region and target region "
                            + "cannot be the same"
            );
        }
    }


    // =========================================================
    // NORMALIZE REGION
    // =========================================================

    private String normalizeRegionCode(
            String regionCode) {


        if (regionCode == null) {

            return null;
        }


        return regionCode
                .trim()
                .toUpperCase();
    }


    // =========================================================
    // MAP ENTITY TO RESPONSE
    // =========================================================

    private FailoverResponse mapToResponse(
            FailoverEvent event) {


        FailoverResponse response =
                new FailoverResponse();


        response.setId(
                event.getId()
        );


        response.setSourceRegion(
                event.getSourceRegion()
        );


        response.setTargetRegion(
                event.getTargetRegion()
        );


        response.setFailoverType(
                event.getFailoverType()
        );


        response.setReason(
                event.getReason()
        );


        response.setTriggeredBy(
                event.getTriggeredBy()
        );


        response.setCreatedAt(
                event.getCreatedAt()
        );


        return response;
    }
}