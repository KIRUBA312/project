package com.example.cdc_synchronization_engine.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.dto.InventoryRequest;
import com.example.cdc_synchronization_engine.dto.InventoryResponse;
import com.example.cdc_synchronization_engine.entity.Inventory;
import com.example.cdc_synchronization_engine.exception.ErrorCode;
import com.example.cdc_synchronization_engine.exception.ResourceAlreadyExistsException;
import com.example.cdc_synchronization_engine.exception.ResourceNotFoundException;
import com.example.cdc_synchronization_engine.kafka.producer.CDCEventProducer;
import com.example.cdc_synchronization_engine.mapper.InventoryMapper;
import com.example.cdc_synchronization_engine.repository.InventoryRepository;
import com.example.cdc_synchronization_engine.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final InventoryMapper inventoryMapper;

    private final CDCEventProducer cdcEventProducer;

    @Override
    @CacheEvict(value = "inventory", allEntries = true)
    public InventoryResponse createInventory(InventoryRequest request) {

        boolean exists =
                inventoryRepository.existsByProductIdAndWarehouseName(
                        request.getProductId(),
                        request.getWarehouseName());

        if (exists) {

            throw new ResourceAlreadyExistsException(
                    ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Inventory already exists for product ID "
                            + request.getProductId()
                            + " in warehouse "
                            + request.getWarehouseName());
        }

        Inventory inventory = inventoryMapper.toEntity(request);

        Inventory savedInventory =
                inventoryRepository.save(inventory);

        InventoryResponse response =
                inventoryMapper.toResponse(savedInventory);

        cdcEventProducer.publishEvent(
                "inventory-events",
                "INVENTORY",
                savedInventory.getId(),
                "CREATE",
                response
        );

        return response;
    }

    @Override
    @CacheEvict(value = "inventory", allEntries = true)
    public InventoryResponse updateStock(
            Long id,
            InventoryRequest request) {

        Inventory inventory =
                inventoryRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "Inventory not found with ID: " + id));

        boolean combinationChanged =
                !inventory.getProductId().equals(request.getProductId())
                        ||
                !inventory.getWarehouseName().equals(request.getWarehouseName());

        if (combinationChanged) {

            boolean exists =
                    inventoryRepository.existsByProductIdAndWarehouseName(
                            request.getProductId(),
                            request.getWarehouseName());

            if (exists) {

                throw new ResourceAlreadyExistsException(
                        ErrorCode.RESOURCE_ALREADY_EXISTS,
                        "Inventory already exists for product ID "
                                + request.getProductId()
                                + " in warehouse "
                                + request.getWarehouseName());
            }
        }

        inventory.setProductId(request.getProductId());
        inventory.setWarehouseName(request.getWarehouseName());
        inventory.setAvailableQuantity(request.getAvailableQuantity());
        inventory.setReservedQuantity(request.getReservedQuantity());

        Inventory updatedInventory =
                inventoryRepository.save(inventory);

        InventoryResponse response =
                inventoryMapper.toResponse(updatedInventory);

        cdcEventProducer.publishEvent(
                "inventory-events",
                "INVENTORY",
                updatedInventory.getId(),
                "UPDATE",
                response
        );

        return response;
    }

    @Override
    @CacheEvict(value = "inventory", allEntries = true)
    public void deleteInventory(Long id) {

        Inventory inventory =
                inventoryRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "Inventory not found with ID: " + id));

        inventoryRepository.delete(inventory);

        cdcEventProducer.publishEvent(
                "inventory-events",
                "INVENTORY",
                id,
                "DELETE",
                null
        );
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "inventory", key = "#id")
    public InventoryResponse getInventory(Long id) {

        Inventory inventory =
                inventoryRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "Inventory not found with ID: " + id));

        return inventoryMapper.toResponse(inventory);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("inventory")
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }
}