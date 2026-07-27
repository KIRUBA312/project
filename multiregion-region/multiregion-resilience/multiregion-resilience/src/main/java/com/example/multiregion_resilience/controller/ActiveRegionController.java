package com.example.multiregion_resilience.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.exception.ErrorCode;
import com.example.multiregion_resilience.exception.InvalidOperationException;
import com.example.multiregion_resilience.exception.ResourceNotFoundException;
import com.example.multiregion_resilience.repository.RegionRepository;
import com.example.multiregion_resilience.service.ActiveRegionService;

@RestController
@RequestMapping("/api/active-region")
public class ActiveRegionController {

    private final ActiveRegionService activeRegionService;

    private final RegionRepository regionRepository;


    public ActiveRegionController(
            ActiveRegionService activeRegionService,
            RegionRepository regionRepository) {

        this.activeRegionService =
                activeRegionService;

        this.regionRepository =
                regionRepository;
    }

    @GetMapping
    public ResponseEntity<Region> getActiveRegion() {

        Region activeRegion =
                activeRegionService
                        .getActiveRegion();

        return ResponseEntity.ok(
                activeRegion
        );
    }


    @GetMapping("/code")
    public ResponseEntity<String> getActiveRegionCode() {

        String regionCode =
                activeRegionService
                        .getActiveRegionCode();

        return ResponseEntity.ok(
                regionCode
        );
    }

    @GetMapping("/check/{regionCode}")
    public ResponseEntity<Boolean> isActiveRegion(
            @PathVariable String regionCode) {

        if (regionCode == null
                || regionCode.isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Region code cannot be empty"
            );
        }

        boolean active =
                activeRegionService
                        .isActiveRegion(
                                regionCode
                        );

        return ResponseEntity.ok(
                active
        );
    }


    @PostMapping("/switch")
    public ResponseEntity<String> switchActiveRegion(
            @RequestParam String failedRegion,
            @RequestParam String targetRegion) {

        if (failedRegion == null
                || failedRegion.isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failed region cannot be empty"
            );
        }

        if (targetRegion == null
                || targetRegion.isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Target region cannot be empty"
            );
        }

        if (failedRegion
                .trim()
                .equalsIgnoreCase(
                        targetRegion.trim()
                )) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failed region and target region "
                    + "cannot be the same"
            );
        }

        Region failed =
                regionRepository
                        .findByRegionCode(
                                failedRegion
                                        .trim()
                                        .toUpperCase()
                        )
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        ErrorCode.REGION_NOT_FOUND,
                                        "Failed region not found: "
                                        + failedRegion
                                )
                        );

        Region target =
                regionRepository
                        .findByRegionCode(
                                targetRegion
                                        .trim()
                                        .toUpperCase()
                        )
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        ErrorCode.REGION_NOT_FOUND,
                                        "Target region not found: "
                                        + targetRegion
                                )
                        );

        activeRegionService
                .switchActiveRegion(
                        failed,
                        target
                );

        String message =
                "Active region switched successfully "
                + "from "
                + failed.getRegionCode()
                + " to "
                + target.getRegionCode();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(message);
    }


    
    @PostMapping("/failback/{regionCode}")
    public ResponseEntity<String> failback(
            @PathVariable String regionCode) {

        if (regionCode == null
                || regionCode.isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Failback region code cannot be empty"
            );
        }

        Region targetRegion =
                regionRepository
                        .findByRegionCode(
                                regionCode
                                        .trim()
                                        .toUpperCase()
                        )
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        ErrorCode.REGION_NOT_FOUND,
                                        "Failback region not found: "
                                        + regionCode
                                )
                        );

        activeRegionService
                .failbackToRegion(
                        targetRegion
                );

        String message =
                "Failback completed successfully "
                + "to region "
                + targetRegion.getRegionCode();

        return ResponseEntity.ok(
                message
        );
    }
}