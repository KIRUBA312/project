package com.example.financialservice.dto;

import java.util.List;

public class TransactionHistoryResponseDto {

	private List<TransactionResponseDto> transactions;
	private int currentPage;
	private int totalPages;
	private long totalElements;
	
	public TransactionHistoryResponseDto() {}
	
	public List<TransactionResponseDto> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionResponseDto> transactions) {
		this.transactions = transactions;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	
	
	
}
