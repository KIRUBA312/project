package com.example.financialservice.service;

import java.util.List;


import com.example.financialservice.dto.AccountRequestDto;
import com.example.financialservice.dto.AccountResponseDto;

public interface AccountService {

	AccountResponseDto createAccount(AccountRequestDto dto);

	List<AccountResponseDto> getAllAccounts();

	AccountResponseDto getAccountById(Long id);

	AccountResponseDto updateAccount(Long id, AccountRequestDto dto);

	void deleteAccount(Long id);

}
