package com.example.financialservice.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.financialservice.entity.Transaction;
import com.example.financialservice.enums.TransactionStatus;
import com.example.financialservice.repository.TransactionRepository;

@Component
public class ReconciliationScheduler {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void dailyReconciliation() {
		
		System.out.println(
				"Reconciliation Started : "+ LocalDateTime.now());
		List<Transaction> transactions = 
				transactionRepository.findAll();
		int successCount = 0;
		int failedCount = 0;
		
		for(Transaction transaction : transactions) {
			if (transaction.getStatus() == TransactionStatus.SUCCESS) {
				
				successCount++;
				
			}
			if (transaction.getStatus() == TransactionStatus.FAILED) {
				failedCount++;
			}
		}
		System.out.println("Total Transactions :"+transactions.size());
		System.out.println("Successful Transactions :"+successCount);
		System.out.println("Failed Transactions : "+failedCount);
		System.out.println("Reconciliation Completed :"+LocalDateTime.now());
	}
	
}
