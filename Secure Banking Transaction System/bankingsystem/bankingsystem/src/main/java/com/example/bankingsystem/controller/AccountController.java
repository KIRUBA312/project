package com.example.bankingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingsystem.dto.AccountRequestDto;
import com.example.bankingsystem.dto.AccountResponseDto;
import com.example.bankingsystem.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<AccountResponseDto> createAccount(
			@RequestBody AccountRequestDto dto){
		
		return ResponseEntity.ok(accountService.createAccount(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<AccountResponseDto>> getAllAccounts(){
		return ResponseEntity.ok(accountService.getAllAccounts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountResponseDto> getAccountById(
			@PathVariable Long id){
		
		return ResponseEntity.ok(accountService.getAccountById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AccountResponseDto> updateAccount(
			@PathVariable Long id,
			@RequestBody AccountRequestDto dto){
		
		return ResponseEntity.ok(accountService.updateAccount(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(
			@PathVariable Long id){
		
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account deleted successfully");
		
	}
	
}
