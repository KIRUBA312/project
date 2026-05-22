package com.example.financialservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.financialservice.enums.AccountStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "account_id")
	private Long accountId;
	
	@Column(name = "account_holder_name")
	private String accountHolderName;
	
	@Column(name = "account_number",unique = true)
	private String accountNumber;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING)
	
	@Column(name = "status")
	private AccountStatus status;
	
	@Column(name = "create_at")
	private LocalDateTime createdAt;
	
	@Version
	@Column(name = "version")
	private Long version;
	
	public Account() {}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
	

}
