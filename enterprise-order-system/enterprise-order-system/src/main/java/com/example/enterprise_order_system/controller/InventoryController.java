package com.example.enterprise_order_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enterprise_order_system.dto.InventoryRequestDto;
import com.example.enterprise_order_system.dto.InventoryResponseDto;
import com.example.enterprise_order_system.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping
	public ResponseEntity<InventoryResponseDto> createInventory(
			@Valid @RequestBody InventoryRequestDto request){
		return new ResponseEntity<>(inventoryService.createInventory(request),
				HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<InventoryResponseDto> getInventoryById(
			@PathVariable Long id){
		return ResponseEntity.ok(inventoryService.getInventoryById(id));
	}
	@GetMapping
	public ResponseEntity<List<InventoryResponseDto>> getAllInventory(){
		return ResponseEntity.ok(inventoryService.getAllInventory());
	}
	@PutMapping("/{id}")
	public ResponseEntity<InventoryResponseDto> updateInventory(
			@PathVariable Long id,@RequestBody InventoryRequestDto request){
		return ResponseEntity.ok(inventoryService
				.updateInventory(id,request));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInventory(@PathVariable Long id){
		inventoryService.deleteInventory(id);
		return ResponseEntity.ok("Inventory Deleted Successfully");
	}
	@PostMapping("/reduce-stock/{id}/{quantity}")
	public ResponseEntity<String> reduceStock(
			@PathVariable Long id,@PathVariable Integer quantity){
		inventoryService.reduceStock(id, quantity);
		return ResponseEntity.ok("Stock reduced Successfully");
	}
	@PostMapping("/add-stock/{id}/{quantity}")
	public ResponseEntity<String> addStock(
			@PathVariable Long id,
			@PathVariable Integer quantity){
		inventoryService.addStock(id,quantity);
		return ResponseEntity.ok("Stock Added SuccessFully");
	}
	
}
