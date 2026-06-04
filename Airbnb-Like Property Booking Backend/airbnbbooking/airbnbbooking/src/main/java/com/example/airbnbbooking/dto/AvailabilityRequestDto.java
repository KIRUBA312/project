package com.example.airbnbbooking.dto;

import java.time.LocalDate;

public class AvailabilityRequestDto {

	private Long propertyId;
	private LocalDate availableFrom;
	private LocalDate availableTo;
	
	public Long getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}
	public LocalDate getAvailableFrom() {
		return availableFrom;
	}
	public void setAvailableFrom(LocalDate availableFrom) {
		this.availableFrom = availableFrom;
	}
	public LocalDate getAvailableTo() {
		return availableTo;
	}
	public void setAvailableTo(LocalDate availableTo) {
		this.availableTo = availableTo;
	}
	
	
	
}
