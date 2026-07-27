package com.example.multiregion_resilience.resilience;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.multiregion_resilience.entity.FailoverEvent;
import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.entity.RegionHealth;
import com.example.multiregion_resilience.enums.FailoverType;
import com.example.multiregion_resilience.enums.HealthStatus;
import com.example.multiregion_resilience.repository.FailoverEventRepository;
import com.example.multiregion_resilience.repository.RegionHealthRepository;
import com.example.multiregion_resilience.repository.RegionRepository;
import com.example.multiregion_resilience.service.ActiveRegionService;

import jakarta.transaction.Transactional;

@Component
public class AutomaticFailoverScheduler {

    private final RegionHealthChecker regionHealthChecker;

    private final RegionSelectionService
            regionSelectionService;

    private final RegionHealthRepository
            regionHealthRepository;

    private final FailoverEventRepository
            failoverEventRepository;

    private final RegionRepository
            regionRepository;

    private final boolean failoverEnabled;

    private final boolean automaticFailoverEnabled;

    private final int failureThreshold;

    private final int recoveryThreshold;

    private final String defaultRegion;

    private final ActiveRegionService
            activeRegionService;


    public AutomaticFailoverScheduler(

            RegionHealthChecker regionHealthChecker,

            RegionSelectionService
                    regionSelectionService,

            RegionHealthRepository
                    regionHealthRepository,

            FailoverEventRepository
                    failoverEventRepository,

            RegionRepository
                    regionRepository,

            ActiveRegionService
                    activeRegionService,

            @Value(
                "${multiregion.failover.enabled:true}"
            )
            boolean failoverEnabled,

            @Value(
                "${multiregion.failover.automatic:true}"
            )
            boolean automaticFailoverEnabled,

            @Value(
                "${multiregion.failover.failure-threshold:3}"
            )
            int failureThreshold,

            @Value(
                "${multiregion.failover.recovery-threshold:2}"
            )
            int recoveryThreshold,

            @Value(
                "${multiregion.failover.default-region:ASIA}"
            )
            String defaultRegion) {


        this.regionHealthChecker =
                regionHealthChecker;

        this.regionSelectionService =
                regionSelectionService;

        this.regionHealthRepository =
                regionHealthRepository;

        this.failoverEventRepository =
                failoverEventRepository;

        this.regionRepository =
                regionRepository;

        this.activeRegionService =
                activeRegionService;

        this.failoverEnabled =
                failoverEnabled;

        this.automaticFailoverEnabled =
                automaticFailoverEnabled;

        this.failureThreshold =
                failureThreshold;

        this.recoveryThreshold =
                recoveryThreshold;

        this.defaultRegion =
                defaultRegion
                        .trim()
                        .toUpperCase();
    }


    @Scheduled(
            fixedDelayString =
                    "${multiregion.failover.health-check-interval:10s}"
    )
    @Transactional
    public void monitorRegions() {

        if (!failoverEnabled) {
            return;
        }

        if (!automaticFailoverEnabled) {
            return;
        }

        List<Region> regions =
                regionRepository.findAll();

        for (Region region : regions) {

            try {

                RegionHealth health =
                        regionHealthChecker
                                .checkRegion(region);

                processHealthState(
                        region,
                        health
                );

            } catch (Exception exception) {

                System.err.println(
                        "Error while checking region "
                        + region.getRegionCode()
                        + ": "
                        + exception.getMessage()
                );
            }
        }
    }


    private void processHealthState(
            Region region,
            RegionHealth health) {

        if (region == null || health == null) {

            return;
        }

        if (health.getStatus() == HealthStatus.UNHEALTHY) {

            processUnhealthyRegion( region,health);

            return;
        }

        if (health.getStatus() == HealthStatus.HEALTHY) {

            processHealthyRegion(
                    region,
                    health
            );
        }
    }


    private void processUnhealthyRegion(
            Region region,
            RegionHealth health) {

        if (!isFailureThresholdReached(
                health)) {

            return;
        }

        if (!activeRegionService
                .isActiveRegion(
                        region.getRegionCode()
                )) {

            return;
        }

        initiateFailover(region);
    }


    private void processHealthyRegion(
            Region region,
            RegionHealth health) {

        if (!isRecoveryThresholdReached(
                health)) {

            return;
        }


        System.out.println(
                "Region "
                + region.getRegionCode()
                + " has recovered. "
                + "Recovery count: "
                + health.getSuccessCount()
        );
    }


    private boolean isFailureThresholdReached(
            RegionHealth health) {

        Integer failureCount =
                health.getFailureCount();

        return failureCount != null
                && failureCount >= failureThreshold;
    }
    private boolean isRecoveryThresholdReached(
            RegionHealth health) {

        Integer successCount =
                health.getSuccessCount();

        return successCount != null
                && successCount >= recoveryThreshold;
    }
    private void initiateFailover(
            Region failedRegion) {

        if (failedRegion == null) {
            return;
        }

        if (failedRegion.getStatus()
                != com.example.multiregion_resilience.enums.RegionStatus.ACTIVE) {

            return;
        }

        Region currentActiveRegion =
                activeRegionService
                        .getActiveRegion();

        if (!currentActiveRegion
                .getRegionCode()
                .equalsIgnoreCase(
                        failedRegion.getRegionCode()
                )) {

            return;
        }

        Region targetRegion =
                regionSelectionService
                        .selectFailoverRegion(
                                failedRegion
                                        .getRegionCode()
                        );

        if (targetRegion == null) {

            System.err.println(
                    "No healthy failover target "
                    + "available for region "
                    + failedRegion
                            .getRegionCode()
            );

            return;
        }

        if (targetRegion.getRegionCode()
                .equalsIgnoreCase(
                        failedRegion
                                .getRegionCode()
                )) {

            return;
        }

        activeRegionService
                .switchActiveRegion(
                        failedRegion,
                        targetRegion
                );

        createFailoverEvent(
                failedRegion,
                targetRegion
        );

        System.out.println(
                "Automatic failover completed: "
                + failedRegion
                        .getRegionCode()
                + " -> "
                + targetRegion
                        .getRegionCode()
        );
    }


    private void createFailoverEvent(
            Region sourceRegion,
            Region targetRegion) {

        FailoverEvent event =
                new FailoverEvent();

        event.setSourceRegion(
                sourceRegion
                        .getRegionCode()
        );

        event.setTargetRegion(
                targetRegion
                        .getRegionCode()
        );

        event.setFailoverType(
                FailoverType.AUTOMATIC
        );

        event.setReason(
                "Automatic failover triggered "
                + "because source region reached "
                + "failure threshold of "
                + failureThreshold
        );

        event.setTriggeredBy(
                "AUTOMATIC_SCHEDULER"
        );

        failoverEventRepository.save(
                event
        );
    }
}