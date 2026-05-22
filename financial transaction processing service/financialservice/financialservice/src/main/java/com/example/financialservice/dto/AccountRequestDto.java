package com.example.financialservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountRequestDto {
	
	@NotBlank(message = "Account holder name is required")
	private String accountHolderName;
	
	@NotNull(message = "Balance is required")
	
	@DecimalMin(value = "0.0",
	message = "Balance must be positive")
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
