package com.example.financialservice.service;

import com.example.financialservice.dto.CreditRequestDto;
import com.example.financialservice.dto.DebitRequestDto;
import com.example.financialservice.dto.TransactionResponseDto;
import com.example.financialservice.dto.TransferRequestDto;

public interface TransactionService {

	TransactionResponseDto debit(DebitRequestDto dto);

	TransactionResponseDto credit(CreditRequestDto dto);

	TransactionResponseDto transfer(TransferRequestDto dto);

	TransactionResponseDto getTransactionById(String transactionId);

}
