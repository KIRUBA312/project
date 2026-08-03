package com.example.cdc_synchronization_engine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductIdAndWarehouseName(
            Long productId,
            String warehouseName
    );

    boolean existsByProductIdAndWarehouseName(
            Long productId,
            String warehouseName
    );
}