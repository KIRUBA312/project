package com.example.cdc_synchronization_engine.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cdc_synchronization_engine.dto.ApiResponse;
import com.example.cdc_synchronization_engine.service.MetricsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService metricsService;

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<Map<String, Object>>>
    summary() {

        Map<String, Object> map =
                new LinkedHashMap<>();

        map.put(
                "totalProcessed",
                metricsService.getTotalProcessed());

        map.put(
                "totalSuccess",
                metricsService.getTotalSuccess());

        map.put(
                "totalFailure",
                metricsService.getTotalFailure());

        map.put(
                "successRate",
                metricsService.getSuccessRate());

        map.put(
                "failureRate",
                metricsService.getFailureRate());

        return ResponseEntity.ok(

                new ApiResponse<>(

                        LocalDateTime.now(),

                        200,

                        "Metrics fetched successfully",

                        map)

        );

    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<?>>
    applicationStatus() {

        return ResponseEntity.ok(

                new ApiResponse<>(

                        LocalDateTime.now(),

                        200,

                        "Application Status",

                        metricsService.getApplicationStatus())

        );

    }

}