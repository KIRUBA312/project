package com.example.financialservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.financialservice.enums.TransactionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name = "from_account")
	private String fromAccount;
	
	@Column(name = "to_account")
	private String toAccount;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "currency")
	private String currency;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TransactionStatus status;
	
	@Column(name = "create_at")
	private LocalDateTime createdAt;
	
	public Transaction() {}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

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

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
