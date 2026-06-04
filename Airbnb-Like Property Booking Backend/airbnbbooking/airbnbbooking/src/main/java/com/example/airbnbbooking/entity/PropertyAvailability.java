package com.example.airbnbbooking.entity;

import jakarta.persistence.Entity;

import java.time.*;

import jakarta.persistence.*;

@Entity
@Table(name = "properties_availability")
public class PropertyAvailability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;
	
	@Column(name = "available_from", nullable = false)
	private LocalDate availableFrom;
	
	@Column(name = "available_to", nullable = false)
	private LocalDate availableTo;
	
	public PropertyAvailability() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
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
