package com.example.stocktradingengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stocktradingengine.dto.TradeResponseDto;
import com.example.stocktradingengine.service.TradeService;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

	@Autowired
	private TradeService tradeService;
	
	@GetMapping
	public ResponseEntity<List<TradeResponseDto>> getAllTrades(){
		return ResponseEntity.ok(tradeService.getAllTrades());
	}
	@GetMapping("/{id}")
	public ResponseEntity<TradeResponseDto>getTradeById(
			@PathVariable Long id){
		return ResponseEntity.ok(tradeService.getTradeById(id));
		
	}
	@GetMapping("/stock/{symbol}")
	public ResponseEntity<List<TradeResponseDto>>getTradesByStock(
			@PathVariable String symbol){
		return ResponseEntity.ok(
				tradeService.getTradesByStock(symbol));
	}
	
}
