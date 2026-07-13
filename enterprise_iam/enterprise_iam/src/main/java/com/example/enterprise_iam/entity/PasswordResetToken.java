package com.example.enterprise_iam.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "token",length = 255)
	private String token;
	
	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;
	
	@Column(name = "used")
	private Boolean used;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public PasswordResetToken() {}

	public PasswordResetToken(Long id, String token, LocalDateTime expiryDate, Boolean used, User user) {
		super();
		this.id = id;
		this.token = token;
		this.expiryDate = expiryDate;
		this.used = used;
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

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
