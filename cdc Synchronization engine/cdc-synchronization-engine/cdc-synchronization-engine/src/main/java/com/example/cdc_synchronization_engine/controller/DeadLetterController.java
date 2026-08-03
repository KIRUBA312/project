package com.example.cdc_synchronization_engine.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cdc_synchronization_engine.dto.DeadLetterEventResponse;
import com.example.cdc_synchronization_engine.service.DeadLetterRetryService;
import com.example.cdc_synchronization_engine.service.DeadLetterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dead-letter")
@RequiredArgsConstructor
public class DeadLetterController {

    private final DeadLetterService deadLetterService;

    private final DeadLetterRetryService retryService;

    @GetMapping
    public ResponseEntity<List<DeadLetterEventResponse>> getAll() {

        return ResponseEntity.ok(
                deadLetterService.getAll());

    }

    @PostMapping("/{id}/retry")
    public ResponseEntity<String> retry(
            @PathVariable Long id) {

        retryService.retryDeadLetterEvent(id);

        return ResponseEntity.ok(
                "Dead Letter Event Republished Successfully");

    }

}