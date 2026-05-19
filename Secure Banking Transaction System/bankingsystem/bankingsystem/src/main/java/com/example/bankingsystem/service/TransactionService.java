package com.example.bankingsystem.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.bankingsystem.dto.TransactionResponseDto;
import com.example.bankingsystem.dto.TransferRequestDto;

public interface TransactionService {

	String transferMoney(TransferRequestDto dto);

	List<TransactionResponseDto> getAllTransactions();

}
