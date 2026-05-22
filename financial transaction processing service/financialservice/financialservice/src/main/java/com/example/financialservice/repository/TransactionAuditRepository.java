package com.example.financialservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financialservice.entity.TransactionAudit;

@Repository
public interface TransactionAuditRepository extends JpaRepository<TransactionAudit, Long>{

	List<TransactionAudit> findByTransactionId(
			String transactionId);
	
}
