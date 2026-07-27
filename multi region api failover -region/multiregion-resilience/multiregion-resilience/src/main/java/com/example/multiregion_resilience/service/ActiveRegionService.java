package com.example.multiregion_resilience.service;

import com.example.multiregion_resilience.entity.Region;

public interface ActiveRegionService {

    
    Region getActiveRegion();

    String getActiveRegionCode();

    
    void switchActiveRegion(
            Region failedRegion,
            Region targetRegion
    );

    void failbackToRegion(
            Region targetRegion
    );

    boolean isActiveRegion(
            String regionCode
    );
}