package com.example.multiregion_resilience.resilience;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.enums.RegionStatus;
import com.example.multiregion_resilience.repository.RegionRepository;

@Service
public class RegionFailoverExecutor {

    private final RegionRepository regionRepository;

    private final RegionCircuitBreakerService
            regionCircuitBreakerService;

    private final ResilienceFallbackService
            resilienceFallbackService;

    public RegionFailoverExecutor(
            RegionRepository regionRepository,
            RegionCircuitBreakerService
                    regionCircuitBreakerService,
            ResilienceFallbackService
                    resilienceFallbackService) {

        this.regionRepository = regionRepository;
        this.regionCircuitBreakerService =
                regionCircuitBreakerService;
        this.resilienceFallbackService =
                resilienceFallbackService;
    }
    public <T> T execute(Function<String, T> regionOperation){

        List<Region> availableRegions = getAvailableRegions();

        if (availableRegions.isEmpty()) {
            throw new RegionResilienceException(
                    resilienceFallbackService
                            .getAllRegionsUnavailableMessage()
            );
        }
        Throwable lastException = null;
        for (Region region :
                availableRegions) {

            String regionCode =
                    region.getRegionCode();
            try {
                return regionCircuitBreakerService
                        .execute(
                        		() -> regionOperation
                                    .apply(regionCode)
                        );

            } catch (Exception exception) {

                lastException =
                        exception;

            }
        }

        throw new RegionResilienceException(
                resilienceFallbackService
                        .getAllRegionsUnavailableMessage(),
                lastException
        );
    }

    private List<Region> getAvailableRegions() {

        return regionRepository
                .findByStatusAndEnabled(
                        RegionStatus.ACTIVE, true
                )
                .stream()
                .sorted(
                    Comparator.comparing(
                        Region::getPriority )
                ).toList();
    }
}