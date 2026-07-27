package com.example.multiregion_resilience.resilience;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.entity.RegionHealth;
import com.example.multiregion_resilience.enums.HealthStatus;
import com.example.multiregion_resilience.enums.RegionStatus;
import com.example.multiregion_resilience.repository.RegionHealthRepository;
import com.example.multiregion_resilience.repository.RegionRepository;

@Service
public class RegionSelectionService {

    private final RegionRepository regionRepository;
    private final RegionHealthRepository regionHealthRepository;
    private final String selectionStrategy;
    private final boolean selectOnlyHealth;
    private final boolean priorityEnabled;

    public RegionSelectionService(
            RegionRepository regionRepository,
            RegionHealthRepository regionHealthRepository,
            @Value("${region.selection-strategy:PRIORITY}")
            String selectionStrategy,
            @Value("${region.select-only-health:true}")
            boolean selectOnlyHealth,
            @Value("${region.priority-enabled:true}")
            boolean priorityEnabled) {

        this.regionRepository = regionRepository;
        this.regionHealthRepository = regionHealthRepository;
        this.selectionStrategy = selectionStrategy;
        this.selectOnlyHealth = selectOnlyHealth;
        this.priorityEnabled = priorityEnabled;
    }
    
    public Region selectBestRegion() {

        List<Region> regions =
                getAvailableRegions();

        if (regions.isEmpty()) {
            return null;
        }

        if (priorityEnabled
                && "PRIORITY".equalsIgnoreCase(
                        selectionStrategy)) {

            return regions
                    .stream()
                    .min(
                            Comparator.comparing(
                                    Region::getPriority,
                                    Comparator.nullsLast(
                                            Integer::compareTo
                                    )
                            )
                    )
                    .orElse(null);
        }

        return regions.get(0);
    }
   
    public Region selectFailoverRegion(
            String failedRegionCode) {

        if (failedRegionCode == null
                || failedRegionCode.isBlank()) {

            return null;
        }

        return getAvailableRegions()
                .stream()
                .filter(region ->
                        !region.getRegionCode()
                                .equalsIgnoreCase(
                                        failedRegionCode
                                )
                )
                .min(Comparator.comparing(
                                Region::getPriority,
                                Comparator.nullsLast(
                                        Integer::compareTo
                                )
                        )
                )
                .orElse(null);
    }

    public List<Region> getAvailableRegions() {

        List<Region> activeRegions =
                regionRepository
                        .findByStatusAndEnabled(
                                RegionStatus.ACTIVE,
                                true
                        );

        if (!selectOnlyHealth) {

            return sortByPriority( activeRegions);
        }

        List<Region> healthyRegions =
                activeRegions
                        .stream()
                        .filter(this::isHealthy)
                        .toList();

        return sortByPriority( healthyRegions);
    }

    private boolean isHealthy(
            Region region) {

        RegionHealth health =
                regionHealthRepository
                        .findTopByRegionIdOrderByCheckedAtDesc(
                                region.getId()
                        ).orElse(null);

        if (health == null) {
            return false;
        }

        return health.getStatus()
                == HealthStatus.HEALTHY;
    }

    private List<Region> sortByPriority(
            List<Region> regions) {

        return regions
                .stream().sorted(
                        Comparator.comparing(
                                Region::getPriority,
                                Comparator.nullsLast(
                                        Integer::compareTo
                                )
                        )
                )
                .toList();
    }
}