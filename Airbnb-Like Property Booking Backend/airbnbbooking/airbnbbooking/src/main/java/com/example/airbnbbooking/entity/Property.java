package com.example.airbnbbooking.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties")
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title", nullable = false, length = 200)
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "location", nullable = false, length = 150)
	private String location;
	
	@Column(name = "price_per_night", nullable = false)
	private BigDecimal pricePerNight;
	
	@ManyToOne
	@JoinColumn(name = "host_id")
	private User host;
	
	@OneToMany(mappedBy = "property")
	private List<PropertyAvailability> availabilities;
	
	@OneToMany(mappedBy = "property")
	private List<Booking> bookings;
	
	@OneToMany(mappedBy = "property")
	private List<Review>reviews;
	
	public Property() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(BigDecimal pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public List<PropertyAvailability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<PropertyAvailability> availabilities) {
		this.availabilities = availabilities;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
	

}
