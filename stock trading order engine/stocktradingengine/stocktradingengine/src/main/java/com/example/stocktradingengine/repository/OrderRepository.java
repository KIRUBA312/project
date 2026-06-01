package com.example.stocktradingengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stocktradingengine.entity.Order;
import com.example.stocktradingengine.entity.Stock;
import com.example.stocktradingengine.entity.User;
import com.example.stocktradingengine.enums.OrderStatus;
import com.example.stocktradingengine.enums.OrderType;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByUser(User user);
	List<Order> findByStock(Stock stock);
	List<Order> findByStatus(OrderStatus status);
	List<Order> findByOrderType(OrderType orderType);
	List<Order> findByUserId(Long userId);
	List<Order> findByStockId(Long stockId);
	List<Order> findByStockAndOrderTypeAndStatus(Stock stock,
			OrderType orderType,OrderStatus status);
	
}
