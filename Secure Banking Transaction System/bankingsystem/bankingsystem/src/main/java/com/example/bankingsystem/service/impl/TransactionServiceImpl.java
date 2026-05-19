package com.example.bankingsystem.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bankingsystem.dto.TransactionResponseDto;
import com.example.bankingsystem.dto.TransferRequestDto;
import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.AuditLog;
import com.example.bankingsystem.entity.Transaction;
import com.example.bankingsystem.exception.InsufficientBalanceException;
import com.example.bankingsystem.exception.ResourceNotFoundException;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.repository.AuditLogRepository;
import com.example.bankingsystem.repository.TransactionRepository;
import com.example.bankingsystem.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AuditLogRepository auditLogRepository;

	@Transactional
	@Override
	public synchronized String transferMoney(TransferRequestDto dto) {
		// TODO Auto-generated method stub
		Account fromAccount = accountRepository.findByAccountNumber(dto.getFromAccount())
				.orElseThrow(() -> new ResourceNotFoundException("From Account not found"));
		
		Account toAccount = accountRepository.findByAccountNumber(
				dto.getToAccount())
				.orElseThrow(() -> new ResourceNotFoundException("To Account Not found"));
		
		if(fromAccount.getBalance().compareTo(dto.getAmount())<0) {
			throw new InsufficientBalanceException("Insufficient Balance");
		}
		
		fromAccount.setBalance(fromAccount.getBalance()
				.subtract(dto.getAmount()));
		toAccount.setBalance(toAccount.getBalance()
				.add(dto.getAmount()));
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		Transaction transaction = new Transaction();
		transaction.setFromAccount(dto.getFromAccount());
		transaction.setToAccount(dto.getToAccount());
		transaction.setAmount(dto.getAmount());
		transaction.setTransactionType("TRANSFER");
		transaction.setTransactionTime(LocalDateTime.now());
		transactionRepository.save(transaction);
		
		//save audit log
		AuditLog auditLog = new AuditLog();
		auditLog.setAction("Money Transfer");
		auditLog.setPerformedBy("admin");
		auditLog.setPerformedAt(LocalDateTime.now());
		auditLogRepository.save(auditLog);
		
		return "Transfer Successful";
	}

	@Override
	public List<TransactionResponseDto> getAllTransactions() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll().stream()
				.map(this::maptoDto)
				.collect(Collectors.toList());

	}
	
	private TransactionResponseDto maptoDto(Transaction transaction) {
		
		TransactionResponseDto dto = new TransactionResponseDto();
		dto.setId(transaction.getId());
		dto.setFromAccount(transaction.getFromAccount());
		dto.setToAccount(transaction.getToAccount());
		dto.setAmount(transaction.getAmount());
		dto.setTransactionType(transaction.getTransactionType());
		dto.setTransactionTime(transaction.getTransactionTime());
		
		return dto;
	}
	
	
	
	
}
