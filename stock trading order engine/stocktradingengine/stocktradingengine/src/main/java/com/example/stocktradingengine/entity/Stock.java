package com.example.stocktradingengine.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "symbol", nullable = false, unique = true)
	private String symbol;
	
	@Column(name = "company_name", nullable = false)
	private String companyName;
	
	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
	private List<Order> orders;
	
	public Stock() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	

}
