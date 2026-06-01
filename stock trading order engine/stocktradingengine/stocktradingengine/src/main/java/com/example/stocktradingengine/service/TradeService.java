package com.example.stocktradingengine.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.stocktradingengine.dto.TradeResponseDto;

public interface TradeService {

	List<TradeResponseDto> getAllTrades();

	TradeResponseDto getTradeById(Long id);

	List<TradeResponseDto> getTradesByStock(String symbol);

}
