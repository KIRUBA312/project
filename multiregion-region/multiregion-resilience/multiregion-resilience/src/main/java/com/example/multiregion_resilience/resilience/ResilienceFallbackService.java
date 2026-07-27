package com.example.multiregion_resilience.resilience;

import org.springframework.stereotype.Service;

@Service
public class ResilienceFallbackService {

    public <T> T throwRegionUnavailable( String regionCode,
            Throwable throwable) {

        throw new RegionResilienceException(
                "Region " + regionCode
                        + " is currently unavailable",
                throwable
        );
    }
    public String getGracefulDegradationMessage(
            String regionCode) {

        return "Region "
                + regionCode
                + " is temporarily unavailable. "
                + "The system is operating in degraded mode.";
    }
    public String getAllRegionsUnavailableMessage() {

        return "All configured regions are currently "
                + "unavailable. Please try again later.";
    }
    public String getFailoverMessage(
            String sourceRegion,
            String targetRegion) {

        return "Traffic failed over from region "
                + sourceRegion
                + " to region "
                + targetRegion;
    }
}