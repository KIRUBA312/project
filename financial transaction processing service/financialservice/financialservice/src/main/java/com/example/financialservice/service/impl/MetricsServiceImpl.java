package com.example.financialservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financialservice.repository.TransactionRepository;
import com.example.financialservice.service.MetricsService;

@Service
public class MetricsServiceImpl implements MetricsService{

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public long totalTransactions() {
		// TODO Auto-generated method stub
		return transactionRepository.count();
	}
	
}
