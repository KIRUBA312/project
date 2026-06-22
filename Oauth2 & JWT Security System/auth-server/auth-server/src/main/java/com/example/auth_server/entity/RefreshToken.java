package com.example.auth_server.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "token")
	private String token;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;
	
	@Column(name = "revoked")
	private Boolean revoked;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean getRevoked() {
		return revoked;
	}

	public void setRevoked(Boolean revoked) {
		this.revoked = revoked;
	}
	
	

}
