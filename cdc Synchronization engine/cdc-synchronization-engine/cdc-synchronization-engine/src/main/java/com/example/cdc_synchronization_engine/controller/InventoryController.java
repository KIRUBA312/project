package com.example.cdc_synchronization_engine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cdc_synchronization_engine.dto.InventoryRequest;
import com.example.cdc_synchronization_engine.dto.InventoryResponse;
import com.example.cdc_synchronization_engine.service.InventoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(
            @Valid @RequestBody InventoryRequest request) {

        InventoryResponse response =
                inventoryService.createInventory(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody InventoryRequest request) {

        InventoryResponse response =
                inventoryService.updateStock(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(
            @PathVariable Long id) {

        inventoryService.deleteInventory(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventory(
            @PathVariable Long id) {

        InventoryResponse response =
                inventoryService.getInventory(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {

        List<InventoryResponse> response =
                inventoryService.getAllInventory();

        return ResponseEntity.ok(response);
    }
}