package com.example.financialservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DebitRequestDto {

	@NotBlank(message = "Account number is required")
	private String accountNumber;
	
	@NotNull(message = "Amount is required")
	@DecimalMin(value = "1.0",message = "Amount must be greater than 0")
	private BigDecimal amount;
	
	public DebitRequestDto() {}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	
}
