package com.example.enterprise_order_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_order_system.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	Optional<Inventory> findByProductName(String productName);
	boolean existsByProductName(String productName);
	
}
