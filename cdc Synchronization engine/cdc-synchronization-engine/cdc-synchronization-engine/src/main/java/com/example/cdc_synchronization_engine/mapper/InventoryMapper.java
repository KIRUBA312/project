package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.InventoryRequest;
import com.example.cdc_synchronization_engine.dto.InventoryResponse;
import com.example.cdc_synchronization_engine.entity.Inventory;

@Component
public class InventoryMapper {

    public Inventory toEntity(InventoryRequest request){

        Inventory inventory=new Inventory();

        inventory.setProductId(request.getProductId());
        inventory.setWarehouseName(request.getWarehouseName());
        inventory.setAvailableQuantity(request.getAvailableQuantity());
        inventory.setReservedQuantity(request.getReservedQuantity());

        return inventory;
    }

    public InventoryResponse toResponse(Inventory inventory){

        InventoryResponse response=new InventoryResponse();

        response.setId(inventory.getId());
        response.setProductId(inventory.getProductId());
        response.setWarehouseName(inventory.getWarehouseName());
        response.setAvailableQuantity(inventory.getAvailableQuantity());
        response.setReservedQuantity(inventory.getReservedQuantity());
        response.setLastUpdated(inventory.getLastUpdated());

        return response;
    }
}