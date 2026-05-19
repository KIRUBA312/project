package com.example.bankingsystem.dto;

import java.math.BigDecimal;

public class AccountRequestDto {

	private String accountHolderName;
	
	private BigDecimal balance;
	public AccountRequestDto() {}
	
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
}
