package com.example.stocktradingengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stocktradingengine.entity.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long>{
	
	List<Trade> findByBuyOrderId(Long buyOrderId);
	List<Trade> findBySellOrderId(Long sellOrderId);
	List<Trade> findTop10ByOrderByExecutedAtDesc();

}
