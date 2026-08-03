package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.OrderItemRequest;
import com.example.cdc_synchronization_engine.dto.OrderItemResponse;
import com.example.cdc_synchronization_engine.entity.Order_items;

@Component
public class OrderItemMapper {

    public Order_items toEntity(
            OrderItemRequest request,
            Long orderId) {

        Order_items item = new Order_items();

        item.setOrderId(orderId);
        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());
        item.setUnitPrice(request.getUnitPrice());
        item.setTotalPrice(request.getTotalPrice());

        return item;
    }

    public OrderItemResponse toResponse(
            Order_items item) {

        OrderItemResponse response =
                new OrderItemResponse();

        response.setId(item.getId());
        response.setOrderId(item.getOrderId());
        response.setProductId(item.getProductId());
        response.setQuantity(item.getQuantity());
        response.setUnitPrice(item.getUnitPrice());
        response.setTotalPrice(item.getTotalPrice());
        response.setCreatedAt(item.getCreatedAt());

        return response;
    }
}