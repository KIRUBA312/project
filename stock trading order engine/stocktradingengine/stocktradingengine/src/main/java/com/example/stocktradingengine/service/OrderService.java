package com.example.stocktradingengine.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.stocktradingengine.dto.BuyOrderRequestDto;
import com.example.stocktradingengine.dto.OrderResponseDto;
import com.example.stocktradingengine.dto.SellOrderRequestDto;

public interface OrderService {

	OrderResponseDto placeBuyOrder(BuyOrderRequestDto dto);

	OrderResponseDto placeSellOrder(SellOrderRequestDto dto);

	OrderResponseDto getOrderById(Long id);

	List<OrderResponseDto> getOrderByUser(Long userid);

	String cancelOrder(Long id);

}
