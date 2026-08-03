package com.example.cdc_synchronization_engine.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.cdc_synchronization_engine.dto.ApiResponse;
import com.example.cdc_synchronization_engine.dto.ApiStatusResponse;
import com.example.cdc_synchronization_engine.service.HealthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthController {

    private final HealthService healthService;

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<ApiStatusResponse>> getStatus() {

        ApiStatusResponse status =
                healthService.getApplicationHealth();

        ApiResponse<ApiStatusResponse> response =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        200,
                        "Application Status Retrieved Successfully",
                        status
                );

        return ResponseEntity.ok(response);

    }

}