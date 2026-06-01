package com.example.stocktradingengine.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stocktradingengine.dto.TradeResponseDto;
import com.example.stocktradingengine.entity.Trade;
import com.example.stocktradingengine.exception.ResourceNotFoundException;
import com.example.stocktradingengine.repository.TradeRepository;
import com.example.stocktradingengine.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService{
	
	@Autowired
	private TradeRepository tradeRepository;

	@Override
	public List<TradeResponseDto> getAllTrades() {
		// TODO Auto-generated method stub
		return tradeRepository.findAll().stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}

	@Override
	public TradeResponseDto getTradeById(Long id) {
		// TODO Auto-generated method stub
		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Trade not found"));
		return maptoResponse(trade);
	}

	@Override
	public List<TradeResponseDto> getTradesByStock(String symbol) {
		// TODO Auto-generated method stub
		return tradeRepository.findAll().stream()
				.filter(t ->t.getBuyOrder()
						.getStock().getSymbol().equalsIgnoreCase(symbol))
				.map(this::maptoResponse).collect(Collectors.toList());
	}
	private TradeResponseDto maptoResponse(Trade trade) {
		TradeResponseDto dto = new TradeResponseDto();
		dto.setId(trade.getId());
		dto.setBuyOrderId(trade.getBuyOrder().getId());
		dto.setSellOrderId(trade.getSellOrder().getId());
		dto.setPrice(trade.getPrice());
		dto.setQuantity(trade.getQuantity());
		dto.setExecutedAt(trade.getExecutedAt());
		
		return dto;
	}

}
