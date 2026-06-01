package com.example.stocktradingengine.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.stocktradingengine.dto.StockRequestDto;
import com.example.stocktradingengine.dto.StockResponseDto;

public interface StockService {

	StockResponseDto createdStock(StockRequestDto dto);

	List<StockResponseDto> getAllStocks();

	StockResponseDto getStocksById(Long id);

	StockResponseDto updateStock(Long id, StockRequestDto dto);

	String deleteStock(Long id);

}
