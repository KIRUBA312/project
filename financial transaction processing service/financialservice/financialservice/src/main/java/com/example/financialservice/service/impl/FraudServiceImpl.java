package com.example.financialservice.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financialservice.entity.FraudRule;
import com.example.financialservice.exception.FraudDetectedException;
import com.example.financialservice.repository.FraudRuleRepository;
import com.example.financialservice.service.FraudService;

@Service
public class FraudServiceImpl implements FraudService{

	@Autowired
	private FraudRuleRepository fraudRuleRepository;

	@Override
	public void validateFraudRules(BigDecimal amount) {
		// TODO Auto-generated method stub
		List<FraudRule> rules = fraudRuleRepository.findByEnabled(true);
		
		for(FraudRule rule : rules) {
			if(amount.compareTo(rule.getMaxAmountLimit())>0) {
				throw new FraudDetectedException("Fraud detected. Amount exceeds limit : "
						+rule.getMaxAmountLimit());
			}
		}
		
	}
	
	
	
}
