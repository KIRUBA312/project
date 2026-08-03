package com.example.cdc_synchronization_engine.mapper;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.OrderRequest;
import com.example.cdc_synchronization_engine.dto.OrderResponse;
import com.example.cdc_synchronization_engine.dto.OrderItemResponse;
import com.example.cdc_synchronization_engine.entity.Order;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequest request) {

        Order order = new Order();

        order.setCustomerId(request.getCustomerId());
        order.setOrderNumber(request.getOrderNumber());
        order.setTotalAmount(request.getTotalAmount());

        
        order.setOrderStatus("PENDING");

        return order;
    }

    public OrderResponse toResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setCustomerId(order.getCustomerId());
        response.setOrderNumber(order.getOrderNumber());
        response.setTotalAmount(order.getTotalAmount());
        response.setOrderStatus(order.getOrderStatus());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());

        
        response.setItems(Collections.emptyList());

        return response;
    }

    public OrderResponse toResponse(
            Order order,
            List<OrderItemResponse> items) {

        OrderResponse response = toResponse(order);

        response.setItems(
                items != null ? items : Collections.emptyList()
        );

        return response;
    }
}