package com.example.financialservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financialservice.dto.AccountRequestDto;
import com.example.financialservice.dto.AccountResponseDto;
import com.example.financialservice.entity.Account;
import com.example.financialservice.enums.AccountStatus;
import com.example.financialservice.exception.ResourceNotFoundException;
import com.example.financialservice.repository.AccountRepository;
import com.example.financialservice.service.AccountService;
import com.example.financialservice.util.AccountNumberGenerator;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountNumberGenerator accountNumberGenerator;

	@Override
	public AccountResponseDto createAccount(AccountRequestDto dto) {
		// TODO Auto-generated method stub
		Account account = new Account();
		account.setAccountHolderName(
				dto.getAccountHolderName());
		account.setBalance(dto.getBalance());
		account.setAccountNumber(
				accountNumberGenerator.generateAccountNumber());
		account.setStatus(AccountStatus.ACTIVE);
		account.setCreatedAt(LocalDateTime.now());
		
		Account savedAccount = accountRepository.save(account);
		return maptoDto(savedAccount);
	}

	@Override
	public List<AccountResponseDto> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAll().stream()
				.map(this::maptoDto)
				.collect(Collectors.toList());
	}

	@Override
	public AccountResponseDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));
		return maptoDto(account);
	}

	@Override
	public AccountResponseDto updateAccount(Long id, AccountRequestDto dto) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException("Account not found"));
		account.setAccountHolderName(dto.getAccountHolderName());
		account.setBalance(dto.getBalance());
		Account updateAccount = accountRepository.save(account);
		return maptoDto(updateAccount);
	}

	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));
		accountRepository.delete(account);
		
	}
	private AccountResponseDto maptoDto(Account account) {
		AccountResponseDto dto = new AccountResponseDto();
		dto.setAccountId(account.getAccountId());
		dto.setAccountHolderName(account.getAccountHolderName());
		dto.setAccountNumber(account.getAccountNumber());
		dto.setBalance(account.getBalance());
		dto.setStatus(account.getStatus().name());
		dto.setCreatedAt(account.getCreatedAt());
		return dto;
	}
	
	
	
}
