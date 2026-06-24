package com.example.enterprise_order_system.service;

import java.util.List;

import com.example.enterprise_order_system.dto.InventoryRequestDto;
import com.example.enterprise_order_system.dto.InventoryResponseDto;

import jakarta.validation.Valid;

public interface InventoryService {

	InventoryResponseDto createInventory(@Valid InventoryRequestDto request);

	InventoryResponseDto getInventoryById(Long id);

	List<InventoryResponseDto> getAllInventory();

	InventoryResponseDto updateInventory(Long id, 
			InventoryRequestDto request);

	void deleteInventory(Long id);

	void reduceStock(Long id, Integer quantity);

	void addStock(Long id, Integer quantity);

}
