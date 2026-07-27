package com.example.multiregion_resilience.resilience;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.multiregion_resilience.entity.FailoverEvent;
import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.entity.RegionHealth;
import com.example.multiregion_resilience.enums.FailoverType;
import com.example.multiregion_resilience.enums.HealthStatus;
import com.example.multiregion_resilience.enums.RegionStatus;
import com.example.multiregion_resilience.repository.FailoverEventRepository;
import com.example.multiregion_resilience.repository.RegionHealthRepository;
import com.example.multiregion_resilience.repository.RegionRepository;
import com.example.multiregion_resilience.service.ActiveRegionService;

@Component
public class AutomaticRecoveryScheduler {

    private final RegionRepository regionRepository;

    private final RegionHealthRepository regionHealthRepository;

    private final FailoverEventRepository failoverEventRepository;

    private final ActiveRegionService activeRegionService;

    private final boolean failoverEnabled;

    private final boolean automaticFailoverEnabled;

    private final boolean manualFailbackEnabled;

    private final int recoveryThreshold;

    private final String defaultRegion;


    public AutomaticRecoveryScheduler(
            RegionRepository regionRepository,
            RegionHealthRepository regionHealthRepository,
            FailoverEventRepository failoverEventRepository,
            ActiveRegionService activeRegionService,

            @Value("${multiregion.failover.enabled:true}")
            boolean failoverEnabled,

            @Value("${multiregion.failover.automatic:true}")
            boolean automaticFailoverEnabled,

            @Value("${multiregion.failover.manual-failback-enabled:true}")
            boolean manualFailbackEnabled,

            @Value("${multiregion.failover.recovery-threshold:2}")
            int recoveryThreshold,

            @Value("${multiregion.failover.default-region:ASIA}")
            String defaultRegion) {

        this.regionRepository =regionRepository;

        this.regionHealthRepository =regionHealthRepository;

        this.failoverEventRepository = failoverEventRepository;

        this.activeRegionService = activeRegionService;

        this.failoverEnabled = failoverEnabled;

        this.automaticFailoverEnabled = automaticFailoverEnabled;

        this.manualFailbackEnabled = manualFailbackEnabled;

        this.recoveryThreshold = recoveryThreshold;

        this.defaultRegion =defaultRegion
                        .trim()
                        .toUpperCase();
    }

   
    @Scheduled(
            fixedDelayString =
                    "${multiregion.failover.health-check-interval:10s}"
    )
    public void monitorRecovery() {

        if (!failoverEnabled) {

            return;
        }

        if (!automaticFailoverEnabled) {

            return;
        }

        if (manualFailbackEnabled) {

            return;
        }

        Optional<Region> defaultRegionOptional =
                regionRepository
                        .findByRegionCode(
                                defaultRegion
                        );

        if (defaultRegionOptional.isEmpty()) {

            return;
        }

        Region originalRegion = defaultRegionOptional.get();

        if (originalRegion.getStatus()!= RegionStatus.FAILED){

            return;
        }

        Optional<RegionHealth> healthOptional =
                regionHealthRepository
                        .findTopByRegionIdOrderByCheckedAtDesc(
                                originalRegion.getId()
                        );

        if (healthOptional.isEmpty()) {

            return;
        }

        RegionHealth latestHealth = healthOptional.get();

        if (latestHealth.getStatus()!= HealthStatus.HEALTHY) {

            return;
        }

        if (!isRecoveryThresholdReached( latestHealth)) {

            return;
        }

        performAutomaticRecovery( originalRegion );
    }


    private boolean isRecoveryThresholdReached(
            RegionHealth health) {

        if (health == null) {
            return false;
        }

        Integer successCount = health.getSuccessCount();


        return successCount != null
                && successCount >= recoveryThreshold;
    }


    private void performAutomaticRecovery(
            Region originalRegion) {

        Region currentActiveRegion;

        try {

            currentActiveRegion = activeRegionService
                            .getActiveRegion();

        } catch (Exception exception) {

            return;
        }

        if (currentActiveRegion == null) {

            return;
        }

        if (currentActiveRegion.getRegionCode()
                .equalsIgnoreCase(
                        originalRegion
                                .getRegionCode())) {
            return;
        }

        if (currentActiveRegion.getId().equals(
                        originalRegion.getId())) {
            return;
        }

        activeRegionService .failbackToRegion(
                        originalRegion );


        createRecoveryEvent(
                currentActiveRegion,
                originalRegion
        );
    }


    private void createRecoveryEvent(
            Region sourceRegion,
            Region recoveredRegion) {

        FailoverEvent event = new FailoverEvent();

        event.setSourceRegion( sourceRegion.getRegionCode());
        event.setTargetRegion( recoveredRegion
                        .getRegionCode() );
        event.setFailoverType( FailoverType.AUTOMATIC);
        event.setReason(
                "Automatic failback triggered. "
                + "Original region "
                + recoveredRegion.getRegionCode()
                + " recovered successfully "
                + "and reached the configured recovery "
                + "threshold of "
                + recoveryThreshold
        );

        event.setTriggeredBy(
                "AUTOMATIC_RECOVERY_SCHEDULER"
        );

        failoverEventRepository.save(
                event
        );
    }
}