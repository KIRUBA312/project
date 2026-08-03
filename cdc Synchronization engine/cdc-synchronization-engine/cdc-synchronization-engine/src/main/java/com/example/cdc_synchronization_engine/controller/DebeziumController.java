package com.example.cdc_synchronization_engine.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cdc_synchronization_engine.dto.ApiResponse;
import com.example.cdc_synchronization_engine.service.DebeziumConnectorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/debezium")
@RequiredArgsConstructor
public class DebeziumController {

    private final DebeziumConnectorService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register() {

        return ResponseEntity.ok(

                new ApiResponse<>(

                        LocalDateTime.now(),

                        200,

                        "Connector Registered",

                        service.registerConnector())

        );

    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<?>> status() {

        return ResponseEntity.ok(

                new ApiResponse<>(

                        LocalDateTime.now(),

                        200,

                        "Connector Status",

                        service.connectorStatus())

        );

    }

    @PostMapping("/restart")
    public ResponseEntity<ApiResponse<?>> restart() {

        return ResponseEntity.ok(

                new ApiResponse<>(

                        LocalDateTime.now(),

                        200,

                        "Connector Restarted",

                        service.restartConnector())

        );

    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> delete() {

        return ResponseEntity.ok(

                new ApiResponse<>(

                        LocalDateTime.now(),

                        200,

                        "Connector Deleted",

                        service.deleteConnector())

        );

    }

}