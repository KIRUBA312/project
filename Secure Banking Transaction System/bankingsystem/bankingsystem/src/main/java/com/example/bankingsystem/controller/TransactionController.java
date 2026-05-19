package com.example.bankingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingsystem.dto.TransactionResponseDto;
import com.example.bankingsystem.dto.TransferRequestDto;
import com.example.bankingsystem.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transfer")
	public ResponseEntity<String> transferMoney(
			@RequestBody TransferRequestDto dto){
		
		return ResponseEntity.ok(transactionService.transferMoney(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<TransactionResponseDto>> getAllTransactions(){
		
		return ResponseEntity.ok(transactionService.getAllTransactions());
	}
	
}
