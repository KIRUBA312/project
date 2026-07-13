package com.example.enterprise_iam.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "verification_tokens")
public class VerificationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "token",length = 255)
	private String token;
	
	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public VerificationToken() {}
	
	

	public VerificationToken(Long id, String token, LocalDateTime expiryDate, User user) {
		super();
		this.id = id;
		this.token = token;
		this.expiryDate = expiryDate;
		this.user = user;
	}

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

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
