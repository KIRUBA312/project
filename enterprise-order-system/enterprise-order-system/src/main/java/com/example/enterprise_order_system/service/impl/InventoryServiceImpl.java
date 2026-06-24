package com.example.enterprise_order_system.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_order_system.dto.InventoryRequestDto;
import com.example.enterprise_order_system.dto.InventoryResponseDto;
import com.example.enterprise_order_system.entity.Inventory;
import com.example.enterprise_order_system.exception.InventoryException;
import com.example.enterprise_order_system.exception.ResourceNotFoundException;
import com.example.enterprise_order_system.repository.InventoryRepository;
import com.example.enterprise_order_system.service.InventoryService;

import jakarta.validation.Valid;

@Service
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public InventoryResponseDto createInventory(@Valid 
			InventoryRequestDto request) {
		// TODO Auto-generated method stub
		Inventory inventory = new Inventory();
		inventory.setProductName(request.getProductName());
		inventory.setAvailableQuantity(request.getAvailableQuantity());
		inventory.setCreatedAt(LocalDateTime.now());
		Inventory savedInventory = inventoryRepository.save(inventory);
		return maptoresponse(savedInventory);
	}

	private InventoryResponseDto maptoresponse(Inventory inventory) {
		// TODO Auto-generated method stub
		InventoryResponseDto response = new InventoryResponseDto();
		response.setId(inventory.getId());
		response.setProductName(inventory.getProductName());
		response.setAvailableQuantity(inventory.getAvailableQuantity());
		return response;
	}

	@Override
	public InventoryResponseDto getInventoryById(Long id) {
		// TODO Auto-generated method stub
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException(
						"Inventory not found"));
		return maptoresponse(inventory);
	}

	@Override
	public List<InventoryResponseDto> getAllInventory() {
		// TODO Auto-generated method stub
		return inventoryRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}

	@Override
	public InventoryResponseDto updateInventory(Long id, 
			InventoryRequestDto request) {
		// TODO Auto-generated method stub
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Inventory not found"));
		inventory.setProductName(request.getProductName());
		inventory.setAvailableQuantity(request.getAvailableQuantity());
		Inventory updateInventory = inventoryRepository.save(inventory);
		
		return maptoresponse(updateInventory);
	}

	@Override
	public void deleteInventory(Long id) {
		// TODO Auto-generated method stub
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Inventory not found"));
		inventoryRepository.delete(inventory);
	}

	@Override
	public void reduceStock(Long id, Integer quantity) {
		// TODO Auto-generated method stub
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Inventory not found"));
		if(inventory.getAvailableQuantity()<quantity) {
			throw new InventoryException("Insufficient Stock Available");
		}
		inventory.setAvailableQuantity(inventory.getAvailableQuantity()-quantity);
		inventoryRepository.save(inventory);
	}

	@Override
	public void addStock(Long id, Integer quantity) {
		// TODO Auto-generated method stub
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Inventory not found"));
		inventory.setAvailableQuantity(inventory.getAvailableQuantity()+quantity);
		inventoryRepository.save(inventory);
		
	}

}
