package com.example.financialservice.service;

import java.math.BigDecimal;

public interface FraudService {
	
	void validateFraudRules(BigDecimal amount);
	

}
