package com.example.financialservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financialservice.entity.Transaction;
import com.example.financialservice.enums.TransactionStatus;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{

	Page<Transaction> findByStatus(
			TransactionStatus status,
			Pageable pageable);
	
	List<Transaction> findByFromAccount(
			String fromAccount);
	
	List<Transaction> findByToAccount(
			String toAccount);
	
}
