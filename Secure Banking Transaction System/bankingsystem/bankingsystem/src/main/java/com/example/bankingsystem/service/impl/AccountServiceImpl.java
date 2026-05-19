package com.example.bankingsystem.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.dto.AccountRequestDto;
import com.example.bankingsystem.dto.AccountResponseDto;
import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.exception.ResourceNotFoundException;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.util.AccountNumberGenerator;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountResponseDto createAccount(AccountRequestDto dto) {
		// TODO Auto-generated method stub
		Account account = new Account();
		account.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
		account.setAccountHolderName(dto.getAccountHolderName());
		account.setBalance(dto.getBalance());
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
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));
		account.setAccountHolderName(dto.getAccountHolderName());
		account.setBalance(dto.getBalance());
		
		Account update = accountRepository.save(account);
		
		return maptoDto(update);
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
		dto.setId(account.getId());
		dto.setAccountNumber(account.getAccountNumber());
		dto.setAccountHolderName(account.getAccountHolderName());
		dto.setBalance(account.getBalance());
		dto.setCreatedAt(account.getCreatedAt());
		
		return dto;
	}

}
