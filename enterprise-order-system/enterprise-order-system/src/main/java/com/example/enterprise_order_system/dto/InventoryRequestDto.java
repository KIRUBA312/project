package com.example.enterprise_order_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class InventoryRequestDto {

	@NotBlank(message = "Product name is required")
	private String productName;
	@Min(value = 0, message = "Quantity cannot be negative")
	private Integer availableQuantity;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
	
	
}
