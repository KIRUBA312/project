package com.example.enterprise_iam.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mfa_secrets")
public class MFASecret {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "secret",length = 255)
	private String secret;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public MFASecret() {}

	public MFASecret(Long id, String secret, Boolean enabled, User user) {
		super();
		this.id = id;
		this.secret = secret;
		this.enabled = enabled;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
