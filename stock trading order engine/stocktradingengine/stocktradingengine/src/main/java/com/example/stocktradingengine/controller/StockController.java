package com.example.stocktradingengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stocktradingengine.dto.StockRequestDto;
import com.example.stocktradingengine.dto.StockResponseDto;
import com.example.stocktradingengine.service.StockService;

@RestController
@RequestMapping("api/stocks")
public class StockController {

	@Autowired
	private StockService stockService;
	
	@PostMapping
	public ResponseEntity<StockResponseDto> createdStock(
			@RequestBody StockRequestDto dto){
		return ResponseEntity.ok(stockService.createdStock(dto));
		
	}
	@GetMapping
	public ResponseEntity<List<StockResponseDto>> getAllStocks(){
		return ResponseEntity.ok(stockService.getAllStocks());
	}
	@GetMapping("/{id}")
	public ResponseEntity<StockResponseDto> getStockById(
			@PathVariable Long id){
		
		return ResponseEntity.ok(stockService.getStocksById(id));		
	}
	@PutMapping("/{id}")
	public ResponseEntity<StockResponseDto> updateStock(
			@PathVariable Long id,@RequestBody StockRequestDto dto){
		return ResponseEntity.ok(stockService.updateStock(id, dto));
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStock(
			@PathVariable Long id){
		return ResponseEntity.ok(stockService.deleteStock(id));
	}
	
	
	
}
