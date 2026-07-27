package com.example.multiregion_resilience.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.entity.RegionHealth;
import com.example.multiregion_resilience.enums.HealthStatus;
import com.example.multiregion_resilience.enums.RegionStatus;
import com.example.multiregion_resilience.exception.ErrorCode;
import com.example.multiregion_resilience.exception.InvalidOperationException;
import com.example.multiregion_resilience.exception.ResourceNotFoundException;
import com.example.multiregion_resilience.repository.RegionHealthRepository;
import com.example.multiregion_resilience.repository.RegionRepository;
import com.example.multiregion_resilience.service.ActiveRegionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ActiveRegionServiceImpl
        implements ActiveRegionService {

    private final RegionRepository regionRepository;

    private final RegionHealthRepository
            regionHealthRepository;

    private final String defaultRegion;


    public ActiveRegionServiceImpl(
            RegionRepository regionRepository,
            RegionHealthRepository regionHealthRepository,
            @Value("${multiregion.failover.default-region:ASIA}")
            String defaultRegion) {

        this.regionRepository =
                regionRepository;

        this.regionHealthRepository =
                regionHealthRepository;

        this.defaultRegion =
                defaultRegion
                        .trim()
                        .toUpperCase();
    }


    /**
     * Returns the currently active and enabled region.
     *
     * Normally only one region should have ACTIVE status
     * in ACTIVE-PASSIVE failover mode.
     */
    @Override
    public Region getActiveRegion() {

        List<Region> activeRegions =
                regionRepository.findByStatusAndEnabled(
                        RegionStatus.ACTIVE,
                        true
                );

        if (activeRegions.isEmpty()) {

            throw new ResourceNotFoundException(
                    ErrorCode.REGION_NOT_FOUND,
                    "No active region is currently available"
            );
        }

        return activeRegions
                .stream()
                .min(
                        Comparator.comparing(
                                Region::getPriority,
                                Comparator.nullsLast(
                                        Integer::compareTo
                                )
                        )
                )
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                ErrorCode.REGION_NOT_FOUND,
                                "No active region is currently available"
                        )
                );
    }


    /**
     * Returns active region code.
     */
    @Override
    public String getActiveRegionCode() {

        return getActiveRegion()
                .getRegionCode();
    }


    /**
     * Switch active traffic from failed region
     * to healthy target region.
     *
     * Example:
     *
     * ASIA    ACTIVE
     * EUROPE  STANDBY
     *
     * After failover:
     *
     * ASIA    FAILED
     * EUROPE  ACTIVE
     */
    @Override
    public void switchActiveRegion(
            Region failedRegion,
            Region targetRegion) {

        validateRegions(
                failedRegion,
                targetRegion
        );

        if (failedRegion.getId()
                .equals(targetRegion.getId())) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failed region and target region "
                    + "cannot be the same"
            );
        }


        if (!Boolean.TRUE.equals(
                failedRegion.getEnabled())) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failed region is disabled: "
                    + failedRegion.getRegionCode()
            );
        }


        if (!Boolean.TRUE.equals(
                targetRegion.getEnabled())) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Target region is disabled: "
                    + targetRegion.getRegionCode()
            );
        }


        if (failedRegion.getStatus()
                != RegionStatus.ACTIVE) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failed region must currently be ACTIVE: "
                    + failedRegion.getRegionCode()
            );
        }


        /*
         * Failover target must be ACTIVE or STANDBY.
         *
         * Normally the target will be STANDBY.
         */
        if (targetRegion.getStatus()
                != RegionStatus.STANDBY
                && targetRegion.getStatus()
                != RegionStatus.ACTIVE) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Target region must be ACTIVE or STANDBY: "
                    + targetRegion.getRegionCode()
            );
        }


        /*
         * Mark old region as FAILED.
         */
        failedRegion.setStatus(
                RegionStatus.FAILED
        );


        /*
         * Mark new region as ACTIVE.
         */
        targetRegion.setStatus(
                RegionStatus.ACTIVE
        );


        regionRepository.save(
                failedRegion
        );

        regionRepository.save(
                targetRegion
        );
    }


    /**
     * Failback traffic to a recovered region.
     *
     * Example before failback:
     *
     * ASIA    FAILED
     * EUROPE  ACTIVE
     *
     * After failback:
     *
     * ASIA    ACTIVE
     * EUROPE  STANDBY
     */
    @Override
    public void failbackToRegion(
            Region targetRegion) {

        if (targetRegion == null) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failback target region cannot be null"
            );
        }


        if (targetRegion.getId() == null) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failback target region ID cannot be null"
            );
        }


        if (targetRegion.getRegionCode() == null
                || targetRegion.getRegionCode().isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failback target region code cannot be empty"
            );
        }


        if (!Boolean.TRUE.equals(
                targetRegion.getEnabled())) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failback target region is disabled: "
                    + targetRegion.getRegionCode()
            );
        }


        /*
         * Get the latest health record for the
         * failback target region.
         */
        RegionHealth latestHealth =
                regionHealthRepository
                        .findTopByRegionIdOrderByCheckedAtDesc(
                                targetRegion.getId()
                        )
                        .orElseThrow(
                                () -> new InvalidOperationException(
                                        ErrorCode.INVALID_OPERATION,
                                        "No health record found for "
                                        + "failback target region: "
                                        + targetRegion.getRegionCode()
                                )
                        );


        /*
         * Failback is allowed only if the target
         * region has recovered and health status
         * is HEALTHY.
         */
        if (latestHealth.getStatus()
                != HealthStatus.HEALTHY) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failback target region is not healthy: "
                    + targetRegion.getRegionCode()
                    + ". Current health status: "
                    + latestHealth.getStatus()
            );
        }


        /*
         * Get current active region.
         *
         * Example:
         *
         * ASIA = FAILED
         * EUROPE = ACTIVE
         */
        Region currentActiveRegion =
                getActiveRegion();


        /*
         * If target is already active,
         * nothing needs to be done.
         */
        if (currentActiveRegion
                .getRegionCode()
                .equalsIgnoreCase(
                        targetRegion.getRegionCode()
                )) {

            return;
        }


        /*
         * Current active region becomes STANDBY.
         */
        currentActiveRegion.setStatus(
                RegionStatus.STANDBY
        );


        /*
         * Recovered target region becomes ACTIVE.
         *
         * This allows a FAILED region to recover
         * and become ACTIVE again.
         */
        targetRegion.setStatus(
                RegionStatus.ACTIVE
        );


        regionRepository.save(
                currentActiveRegion
        );

        regionRepository.save(
                targetRegion
        );
    }


    /**
     * Checks whether a region is currently active.
     */
    @Override
    public boolean isActiveRegion(
            String regionCode) {

        if (regionCode == null
                || regionCode.isBlank()) {

            return false;
        }


        try {

            return getActiveRegion()
                    .getRegionCode()
                    .equalsIgnoreCase(
                            regionCode.trim()
                    );

        } catch (ResourceNotFoundException exception) {

            return false;
        }
    }


    /**
     * Returns configured default region.
     */
    public String getDefaultRegion() {

        return defaultRegion;
    }


    /**
     * Validates source and target regions
     * used during automatic failover.
     */
    private void validateRegions(
            Region failedRegion,
            Region targetRegion) {

        if (failedRegion == null) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failed region cannot be null"
            );
        }


        if (targetRegion == null) {

            throw new ResourceNotFoundException(
                    ErrorCode.REGION_NOT_FOUND,
                    "Target failover region not found"
            );
        }


        if (failedRegion.getId() == null) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failed region ID cannot be null"
            );
        }


        if (targetRegion.getId() == null) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Target region ID cannot be null"
            );
        }


        if (failedRegion.getRegionCode() == null
                || failedRegion.getRegionCode().isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failed region code cannot be null or empty"
            );
        }


        if (targetRegion.getRegionCode() == null
                || targetRegion.getRegionCode().isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Target region code cannot be null or empty"
            );
        }
    }
}