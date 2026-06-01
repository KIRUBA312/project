package com.example.stocktradingengine.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stocktradingengine.dto.StockRequestDto;
import com.example.stocktradingengine.dto.StockResponseDto;
import com.example.stocktradingengine.entity.Stock;
import com.example.stocktradingengine.exception.ResourceNotFoundException;
import com.example.stocktradingengine.repository.StockRepository;
import com.example.stocktradingengine.service.StockService;

@Service
public class StockServiceImpl implements StockService{

	@Autowired
	private StockRepository stockRepository;

	@Override
	public StockResponseDto createdStock(StockRequestDto dto) {
		// TODO Auto-generated method stub
		Stock stock = new Stock();
		
		stock.setSymbol(dto.getSymbol());
		stock.setCompanyName(dto.getCompanyName());
		stock = stockRepository.save(stock);
		return maptoresponse(stock);
	}

	@Override
	public List<StockResponseDto> getAllStocks() {
		// TODO Auto-generated method stub
		return stockRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}

	@Override
	public StockResponseDto getStocksById(Long id) {
		// TODO Auto-generated method stub
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException("Stock not found"));
		return maptoresponse(stock);
	}

	@Override
	public StockResponseDto updateStock(Long id, StockRequestDto dto) {
		// TODO Auto-generated method stub
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException("Stock not found"));
		stock.setSymbol(dto.getSymbol());
		stock.setCompanyName(dto.getCompanyName());
		stock = stockRepository.save(stock);
		return maptoresponse(stock);
	}

	@Override
	public String deleteStock(Long id) {
		// TODO Auto-generated method stub
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() ->new 
						ResourceNotFoundException("stock not found"));
		stockRepository.delete(stock);
		return "Stock deleted successfully";
	}
	
	private StockResponseDto maptoresponse(Stock stock) {
		StockResponseDto dto = new StockResponseDto();
		dto.setId(stock.getId());
		dto.setCompanyName(stock.getCompanyName());
		dto.setSymbol(stock.getSymbol());
		
		return dto;
	}
	
	
	
}
