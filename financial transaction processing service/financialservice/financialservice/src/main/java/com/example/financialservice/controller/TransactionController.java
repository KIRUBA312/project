package com.example.financialservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financialservice.dto.CreditRequestDto;
import com.example.financialservice.dto.DebitRequestDto;
import com.example.financialservice.dto.TransactionResponseDto;
import com.example.financialservice.dto.TransferRequestDto;
import com.example.financialservice.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/debit")
	public ResponseEntity<TransactionResponseDto> debit(
			@Validated
			@RequestBody
			DebitRequestDto dto){
		
		return ResponseEntity.ok(transactionService.debit(dto));
		
	}
	
	@PostMapping("/credit")
	public ResponseEntity<TransactionResponseDto> credit(
			@Validated
			@RequestBody
			CreditRequestDto dto){
		
		return ResponseEntity.ok(transactionService.credit(dto));
		
	}
	@PostMapping("/transfer")
	public ResponseEntity<TransactionResponseDto> transfer(
			@Validated
			@RequestBody
			TransferRequestDto dto){
		
		return ResponseEntity.ok(transactionService.transfer(dto));
		
	}
	@GetMapping("/{transactionId}")
	public ResponseEntity<TransactionResponseDto> getTransaction(
			@PathVariable String transactionId){
		
		return ResponseEntity.ok(
				transactionService.getTransactionById(
						transactionId));	
	}
	
	
}
