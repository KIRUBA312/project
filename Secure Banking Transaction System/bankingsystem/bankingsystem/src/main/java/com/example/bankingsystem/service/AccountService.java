package com.example.bankingsystem.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.bankingsystem.dto.AccountRequestDto;
import com.example.bankingsystem.dto.AccountResponseDto;

public interface AccountService {

	AccountResponseDto createAccount(AccountRequestDto dto);

	List<AccountResponseDto> getAllAccounts();

	AccountResponseDto getAccountById(Long id);

	AccountResponseDto updateAccount(Long id, AccountRequestDto dto);

	void deleteAccount(Long id);

}
