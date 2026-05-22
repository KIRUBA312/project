package com.example.financialservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.financialservice.dto.CreditRequestDto;
import com.example.financialservice.dto.DebitRequestDto;
import com.example.financialservice.dto.TransactionResponseDto;
import com.example.financialservice.dto.TransferRequestDto;
import com.example.financialservice.entity.Account;
import com.example.financialservice.entity.FraudRule;
import com.example.financialservice.entity.IdempotencyKey;
import com.example.financialservice.entity.Transaction;
import com.example.financialservice.entity.TransactionAudit;
import com.example.financialservice.enums.TransactionStatus;
import com.example.financialservice.exception.DuplicateRequestException;
import com.example.financialservice.exception.FraudDetectedException;
import com.example.financialservice.exception.InsufficientBalanceException;
import com.example.financialservice.exception.ResourceNotFoundException;
import com.example.financialservice.repository.AccountRepository;
import com.example.financialservice.repository.FraudRuleRepository;
import com.example.financialservice.repository.IdempotencyKeyRepository;
import com.example.financialservice.repository.TransactionAuditRepository;
import com.example.financialservice.repository.TransactionRepository;
import com.example.financialservice.service.TransactionService;
import com.example.financialservice.util.TransactionIdGenerator;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private FraudRuleRepository fraudRuleRepository;
	
	@Autowired
	private TransactionAuditRepository auditRepository;
	
	@Autowired
	private IdempotencyKeyRepository idempotencyKeyRepository;
	
	@Autowired
	private TransactionIdGenerator transactionIdGenerator;

	@Override
	@Transactional
	@Retryable(maxAttempts = 3)
	public TransactionResponseDto debit(DebitRequestDto dto) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findByAccountNumber(
				dto.getAccountNumber())
				.orElseThrow(() ->
				new ResourceNotFoundException("Account not found"));
		
		if(account.getBalance().compareTo(dto.getAmount())<0) {
			throw new InsufficientBalanceException(
					"Insufficient balance");
		}
		account.setBalance(account.getBalance()
				.subtract(dto.getAmount()));
		accountRepository.save(account);
		Transaction transaction = createTransaction(
				dto.getAccountNumber(),null,dto.getAmount(),"INR");
		
		return maptoDto(transaction);
	}

	@Override
	@Transactional
	@Retryable(maxAttempts = 3)
	public TransactionResponseDto credit(CreditRequestDto dto) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findByAccountNumber(
				dto.getAccountNumber())
				.orElseThrow(() ->
				new ResourceNotFoundException("Account not found"));
		account.setBalance(account.getBalance()
				.add(dto.getAmount()));
		
		accountRepository.save(account);
		Transaction transaction = createTransaction(
				null,
				dto.getAccountNumber(),
				dto.getAmount(),"INR");
		return maptoDto(transaction);
	}

	@Override
	public TransactionResponseDto transfer(TransferRequestDto dto) {
		// TODO Auto-generated method stub
		if(idempotencyKeyRepository.existsByRequestKey(
				dto.getRequestKey())) {
			throw new DuplicateRequestException(
					"Duplicate request");
		}
		List<FraudRule> rules = fraudRuleRepository.findByEnabled(true);
		for(FraudRule rule : rules) {
			if(dto.getAmount()
					.compareTo(rule.getMaxAmountLimit()) >0) {
				throw new FraudDetectedException(
						"Fraud rule triggered");
			}
		}
		Account fromAccount = accountRepository
				.findByAccountNumber(dto.getFromAccount())
				.orElseThrow(() ->
				new ResourceNotFoundException("Sender account not found"));
		
		Account toAccount = accountRepository
				.findByAccountNumber(dto.getToAccount())
				.orElseThrow(() ->
				new ResourceNotFoundException(
						"Receiver account not found"));
		
		if(fromAccount.getBalance().compareTo(dto.getAmount())<0) {
			throw new InsufficientBalanceException(
					"Insufficient balance");
		}
		
		fromAccount.setBalance(fromAccount.getBalance()
				.subtract(dto.getAmount()));
		toAccount.setBalance(toAccount.getBalance()
				.add(dto.getAmount()));
		
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		Transaction transaction = createTransaction(
				dto.getFromAccount(),
				dto.getToAccount(),
				dto.getAmount(),
				dto.getCurrency());
		
		IdempotencyKey key = new IdempotencyKey();
		key.setRequestKey(dto.getRequestKey());
		key.setTransactionId(transaction.getTransactionId());
		idempotencyKeyRepository.save(key);
		createAudit(
				transaction.getTransactionId(),
				"TRANSFER",
				"Money transfered successfully");
		
		return maptoDto(transaction);
	}

	@Override
	public TransactionResponseDto 
	getTransactionById(String transactionId) {
		// TODO Auto-generated method stub
		Transaction transaction = transactionRepository
				.findById(transactionId)
				.orElseThrow(() ->
				new ResourceNotFoundException(
						"Transaction not found"));
		return maptoDto(transaction);
	}
	
	private Transaction createTransaction(
			String fromAccount, String toAccount, 
			BigDecimal amount, String currency) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		transaction.setTransactionId(
				transactionIdGenerator.generateTransactionId());
		transaction.setFromAccount(fromAccount);
		transaction.setToAccount(toAccount);
		transaction.setAmount(amount);
		transaction.setCurrency(currency);
		transaction.setStatus(TransactionStatus.SUCCESS);
		transaction.setCreatedAt(LocalDateTime.now());
		return transactionRepository.save(transaction);
	}
	
	private void createAudit(
			String transactionId, 
			String eventType, 
			String message) {
		// TODO Auto-generated method stub
		TransactionAudit audit = new TransactionAudit();
		
		audit.setTransactionId(transactionId);
		audit.setEventType(eventType);
		audit.setMessage(message);
		audit.setCreatedAt(LocalDateTime.now());
		
		auditRepository.save(audit);
		
	}
	
	private TransactionResponseDto maptoDto(
			Transaction transaction) {
		TransactionResponseDto dto = new TransactionResponseDto();
		dto.setTransactionId(transaction.getTransactionId());
		dto.setFromAccount(transaction.getFromAccount());
		dto.setToAccount(transaction.getToAccount());
		dto.setAmount(transaction.getAmount());
		dto.setCurrency(transaction.getCurrency());
		dto.setStatus(transaction.getStatus().name());
		dto.setCreatedAt(transaction.getCreatedAt());
		
		return dto;
		
		
	}

}
