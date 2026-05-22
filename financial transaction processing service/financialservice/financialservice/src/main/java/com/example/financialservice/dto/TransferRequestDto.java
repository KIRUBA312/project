package com.example.financialservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransferRequestDto {

	@NotBlank(message = "from account is required")
	private String fromAccount;
	
	@NotBlank(message = "to account is required")
	private String toAccount;
	
	@NotNull(message = "Amount is required")
	@DecimalMin(value = "1.0",message = "Amount must be greater than 0")
	private BigDecimal amount;
	
	@NotBlank(message = "Currency is required")
	private String currency;
	
	private String requestKey;
	
	public TransferRequestDto() {}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRequestKey() {
		return requestKey;
	}

	public void setRequestKey(String requestKey) {
		this.requestKey = requestKey;
	}
	
	
	
}
