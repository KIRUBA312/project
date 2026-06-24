package com.example.enterprise_order_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_order_system.entity.OutboxEvent;

@Repository
public interface OutBoxRepository extends JpaRepository<OutboxEvent, Long>{

	List<OutboxEvent> findByStatus(String status);
}
