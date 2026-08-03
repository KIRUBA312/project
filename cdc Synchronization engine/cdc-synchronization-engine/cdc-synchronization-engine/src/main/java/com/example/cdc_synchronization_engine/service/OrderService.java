package com.example.cdc_synchronization_engine.service;

import java.util.List;

import com.example.cdc_synchronization_engine.dto.OrderRequest;
import com.example.cdc_synchronization_engine.dto.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    OrderResponse updateStatus(
            Long id,
            String status
    );

    OrderResponse cancelOrder(Long id);

    OrderResponse getOrder(Long id);

    List<OrderResponse> getAllOrders();
}