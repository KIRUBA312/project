package com.example.multiregion_resilience.dto;

import com.example.multiregion_resilience.enums.*;
import jakarta.validation.constraints.*;

public class RegionRequest {

    @NotBlank(message = "Region code is required")
    @Size(
            min = 2,
            max = 50,
            message = "Region code must be between 2 and 50 characters"
    )
    private String regionCode;


    @NotBlank(message = "Region name is required")
    @Size(
            min = 2,
            max = 100,
            message = "Region name must be between 2 and 100 characters"
    )
    private String regionName;


    @NotBlank(message = "Endpoint URL is required")
    @Size(
            max = 500,
            message = "Endpoint URL cannot exceed 500 characters"
    )
    private String endpointUrl;


    @NotNull(message = "Deployment mode is required")
    private DeploymentMode deploymentMode;


    @NotNull(message = "Priority is required")
    @Min(
            value = 1,
            message = "Priority must be greater than or equal to 1"
    )
    private Integer priority;

    public RegionRequest() {
    }


    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }


    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }


    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }


    public DeploymentMode getDeploymentMode() {
        return deploymentMode;
    }

    public void setDeploymentMode(DeploymentMode deploymentMode) {
        this.deploymentMode = deploymentMode;
    }


    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}