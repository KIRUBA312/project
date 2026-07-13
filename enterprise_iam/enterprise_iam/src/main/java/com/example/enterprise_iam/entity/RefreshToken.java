package com.example.enterprise_iam.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "token", length = 500, nullable = false)
	private String token;
	
	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;
	
	@Column(name = "revoked")
	private Boolean revoked;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public RefreshToken() {}

	public RefreshToken(Long id, String token, LocalDateTime expiryDate, Boolean revoked, User user) {
		super();
		this.id = id;
		this.token = token;
		this.expiryDate = expiryDate;
		this.revoked = revoked;
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

	public Boolean getRevoked() {
		return revoked;
	}

	public void setRevoked(Boolean revoked) {
		this.revoked = revoked;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
