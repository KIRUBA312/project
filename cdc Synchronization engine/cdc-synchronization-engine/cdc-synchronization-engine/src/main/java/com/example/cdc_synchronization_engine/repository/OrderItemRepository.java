package com.example.cdc_synchronization_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.Order_items;

@Repository
public interface OrderItemRepository extends JpaRepository<Order_items, Long> {

    List<Order_items> findByOrderId(Long orderId);

    boolean existsByOrderId(Long orderId);

    void deleteByOrderId(Long orderId);
}