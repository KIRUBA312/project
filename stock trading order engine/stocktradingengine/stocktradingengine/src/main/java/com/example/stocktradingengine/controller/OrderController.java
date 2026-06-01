package com.example.stocktradingengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stocktradingengine.dto.BuyOrderRequestDto;
import com.example.stocktradingengine.dto.OrderResponseDto;
import com.example.stocktradingengine.dto.SellOrderRequestDto;
import com.example.stocktradingengine.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/buy")
	public ResponseEntity<OrderResponseDto>placeBuyOrder(
			@RequestBody BuyOrderRequestDto dto){
		return ResponseEntity.ok(orderService.placeBuyOrder(dto));
	}
	@PostMapping("/sell")
	public ResponseEntity<OrderResponseDto>PlaceSellOrder(
			@RequestBody SellOrderRequestDto dto){
		
		return ResponseEntity.ok(orderService.placeSellOrder(dto));
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDto> getOrderById(
			@PathVariable Long id){
		return ResponseEntity.ok(orderService.getOrderById(id));
		
	}
	@GetMapping("user/{userid}")
	public ResponseEntity<List<OrderResponseDto>>getOrderByUser(
			@PathVariable Long userid){
		return ResponseEntity.ok(orderService.getOrderByUser(userid));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String>cancelOrder(@PathVariable Long id){
		return ResponseEntity.ok(orderService.cancelOrder(id));
	}
	
}
