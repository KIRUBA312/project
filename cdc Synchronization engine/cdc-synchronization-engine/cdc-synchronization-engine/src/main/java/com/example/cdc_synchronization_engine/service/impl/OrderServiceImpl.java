package com.example.cdc_synchronization_engine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.dto.OrderItemResponse;
import com.example.cdc_synchronization_engine.dto.OrderRequest;
import com.example.cdc_synchronization_engine.dto.OrderResponse;
import com.example.cdc_synchronization_engine.entity.Order;
import com.example.cdc_synchronization_engine.entity.Order_items;
import com.example.cdc_synchronization_engine.exception.ErrorCode;
import com.example.cdc_synchronization_engine.exception.ResourceAlreadyExistsException;
import com.example.cdc_synchronization_engine.exception.ResourceNotFoundException;
import com.example.cdc_synchronization_engine.kafka.producer.CDCEventProducer;
import com.example.cdc_synchronization_engine.mapper.OrderItemMapper;
import com.example.cdc_synchronization_engine.mapper.OrderMapper;
import com.example.cdc_synchronization_engine.repository.OrderItemRepository;
import com.example.cdc_synchronization_engine.repository.OrderRepository;
import com.example.cdc_synchronization_engine.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final CDCEventProducer cdcEventProducer;

    private final OrderMapper orderMapper;

    private final OrderItemMapper orderItemMapper;

    @Value("${cdc.kafka.topic.orders}")
    private String orderTopic;

    @Override
    @CacheEvict(value = "orders", allEntries = true)
    public OrderResponse createOrder(OrderRequest request) {

        if (orderRepository.existsByOrderNumber(request.getOrderNumber())) {

            throw new ResourceAlreadyExistsException(
                    ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Order already exists with order number : "
                            + request.getOrderNumber());
        }

        Order order = orderMapper.toEntity(request);

        Order savedOrder = orderRepository.save(order);

        List<Order_items> items = request.getItems()
                .stream()
                .map(item ->
                        orderItemMapper.toEntity(
                                item,
                                savedOrder.getId()))
                .toList();

        List<Order_items> savedItems =
                orderItemRepository.saveAll(items);

        List<OrderItemResponse> itemResponses =
                savedItems.stream()
                        .map(orderItemMapper::toResponse)
                        .toList();

        OrderResponse response =
                orderMapper.toResponse(
                        savedOrder,
                        itemResponses);

        cdcEventProducer.publishEvent(
                orderTopic,
                "Order",
                savedOrder.getId(),
                "CREATE",
                response);

        return response;
    }

    @Override
    @CacheEvict(value = "orders", allEntries = true)
    public OrderResponse updateStatus(Long id,
                                      String status) {

        Order order = findOrderById(id);

        if (status == null || status.isBlank()) {

            throw new IllegalArgumentException(
                    "Order status cannot be empty");
        }

        if ("CANCELLED".equalsIgnoreCase(
                order.getOrderStatus())) {

            throw new IllegalArgumentException(
                    "Cancelled order status cannot be changed");
        }

        order.setOrderStatus(status);

        Order updatedOrder =
                orderRepository.save(order);

        OrderResponse response =
                getOrderWithItems(updatedOrder);

        cdcEventProducer.publishEvent(
                orderTopic,
                "Order",
                updatedOrder.getId(),
                "UPDATE",
                response);

        return response;
    }

    @Override
    @CacheEvict(value = "orders", allEntries = true)
    public OrderResponse cancelOrder(Long id) {

        Order order = findOrderById(id);

        if ("CANCELLED".equalsIgnoreCase(
                order.getOrderStatus())) {

            throw new IllegalArgumentException(
                    "Order is already cancelled");
        }

        order.setOrderStatus("CANCELLED");

        Order cancelledOrder =
                orderRepository.save(order);

        OrderResponse response =
                getOrderWithItems(cancelledOrder);

        cdcEventProducer.publishEvent(
                orderTopic,
                "Order",
                cancelledOrder.getId(),
                "DELETE",
                response);

        return response;
    }

    @Override
    @Cacheable(value = "orders", key = "#id")
    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long id) {

        Order order = findOrderById(id);

        return getOrderWithItems(order);
    }

    @Override
    @Cacheable("orders")
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(this::getOrderWithItems)
                .toList();
    }

    private OrderResponse getOrderWithItems(Order order) {

        List<OrderItemResponse> itemResponses =
                orderItemRepository
                        .findByOrderId(order.getId())
                        .stream()
                        .map(orderItemMapper::toResponse)
                        .toList();

        return orderMapper.toResponse(
                order,
                itemResponses);
    }

    private Order findOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorCode.RESOURCE_NOT_FOUND,
                                "Order not found with ID : " + id));
    }
}