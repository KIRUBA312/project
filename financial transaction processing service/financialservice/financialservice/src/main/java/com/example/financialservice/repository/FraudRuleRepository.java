package com.example.financialservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financialservice.entity.FraudRule;

@Repository
public interface FraudRuleRepository extends JpaRepository<FraudRule, Long> {

	List<FraudRule> findByEnabled(
			boolean enabled);
	
}
