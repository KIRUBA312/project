package com.example.cdc_synchronization_engine.service;

import java.util.List;

import com.example.cdc_synchronization_engine.dto.InventoryRequest;
import com.example.cdc_synchronization_engine.dto.InventoryResponse;

public interface InventoryService {

    InventoryResponse createInventory(InventoryRequest request);

    InventoryResponse updateStock(
            Long id,
            InventoryRequest request
    );

    void deleteInventory(Long id);

    InventoryResponse getInventory(Long id);

    List<InventoryResponse> getAllInventory();
}