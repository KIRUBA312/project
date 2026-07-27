package com.example.multiregion_resilience.resilience;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.entity.RegionHealth;
import com.example.multiregion_resilience.enums.HealthStatus;
import com.example.multiregion_resilience.repository.RegionHealthRepository;
import com.example.multiregion_resilience.repository.RegionRepository;

@Service
public class RegionHealthChecker {

    private final RegionRepository regionRepository;

    private final RegionHealthRepository
            regionHealthRepository;

    private final RestClient restClient;

    public RegionHealthChecker(
            RegionRepository regionRepository,
            RegionHealthRepository regionHealthRepository,
            RestClient.Builder restClientBuilder) {

        this.regionRepository = regionRepository;

        this.regionHealthRepository =
                regionHealthRepository;

        this.restClient =
                restClientBuilder.build();
    }

    public void checkAllRegions() {

        List<Region> regions =
                regionRepository.findAll();

        for (Region region : regions) {

            checkRegion(region);
        }
    }

    public RegionHealth checkRegion(
            Region region) {

        if (region == null) {
            return null;
        }

        RegionHealth health =
                regionHealthRepository
                        .findTopByRegionIdOrderByCheckedAtDesc(
                                region.getId()
                        )
                        .orElseGet(
                                () -> createInitialHealth(
                                        region
                                )
                        );

        long startTime = System.currentTimeMillis();

        try {

            performHealthCheck(region);

            long responseTime =
                    System.currentTimeMillis()
                            - startTime;

            updateSuccessHealth(
                    health,
                    responseTime
            );

        } catch (Exception exception) {

            long responseTime =
                    System.currentTimeMillis()
                            - startTime;

            updateFailureHealth(
                    health,
                    responseTime
            );
        }

        health.setCheckedAt(
                LocalDateTime.now()
        );

        return regionHealthRepository.save(
                health
        );
    }

    private void performHealthCheck(
            Region region) {

        String endpoint =
                region.getEndpointUrl();

        if (endpoint == null
                || endpoint.isBlank()) {

            throw new IllegalStateException(
                    "Region endpoint URL is not configured"
            );
        }

        restClient
                .get()
                .uri(endpoint)
                .retrieve()
                .toBodilessEntity();
    }

    private RegionHealth createInitialHealth(
            Region region) {

        RegionHealth health =
                new RegionHealth();

        health.setRegion(region);

        health.setStatus(
                HealthStatus.UNKNOWN
        );
        health.setFailureCount(0);
        health.setSuccessCount(0);
        health.setCheckedAt(
                LocalDateTime.now()
        );

        return health;
    }

    private void updateSuccessHealth(
            RegionHealth health,
            long responseTime) {

        health.setStatus(
                HealthStatus.HEALTHY
        );

        health.setResponseTimeMs(
                responseTime
        );

        Integer successCount =
                health.getSuccessCount();

        if (successCount == null) {
            successCount = 0;
        }

        health.setSuccessCount(
                successCount + 1
        );

        health.setLastSuccessAt(
                LocalDateTime.now()
        );

        health.setFailureCount(0);
    }

   
    private void updateFailureHealth(
            RegionHealth health,
            long responseTime) {

        health.setStatus(
                HealthStatus.UNHEALTHY
        );

        health.setResponseTimeMs(
                responseTime
        );

        Integer failureCount =
                health.getFailureCount();

        if (failureCount == null) {
            failureCount = 0;
        }

        health.setFailureCount(
                failureCount + 1
        );

        health.setSuccessCount(0);
        health.setLastFailureAt(
                LocalDateTime.now()
        );
    }
}