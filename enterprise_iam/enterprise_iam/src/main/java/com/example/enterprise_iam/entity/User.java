package com.example.enterprise_iam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name",length = 100)
	private String firstName;
	
	@Column(name = "last_name",length = 100)
	private String lastName;
	
	@Column(name = "email",unique = true,length = 150)
	private String email;
	
	@Column(name = "password",length = 255)
	private String password;
	
	@Column(name = "phone", length = 20)
	private String phone;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;
	
	@Column(name = "failed_attempts")
	private Integer failedAttempts;
	
	@Column(name = "email_verified")
	private Boolean emailVerified;
	
	@Column(name = "mfa_enabled")
	private Boolean mfaEnabled;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<UserRole> userRoles;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<RefreshToken> refreshTokens;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<VerificationToken> verificationTokens;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<PasswordResetToken> passwordResetTokens;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<UserSession> userSessions;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<MFASecret> mfaSecrets;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<LoginHistory> loginHistories;
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Integer getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(Integer failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public Boolean getMfaEnabled() {
		return mfaEnabled;
	}

	public void setMfaEnabled(Boolean mfaEnabled) {
		this.mfaEnabled = mfaEnabled;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<RefreshToken> getRefreshTokens() {
		return refreshTokens;
	}

	public void setRefreshTokens(List<RefreshToken> refreshTokens) {
		this.refreshTokens = refreshTokens;
	}

	public List<VerificationToken> getVerificationTokens() {
		return verificationTokens;
	}

	public void setVerificationTokens(List<VerificationToken> verificationTokens) {
		this.verificationTokens = verificationTokens;
	}

	public List<PasswordResetToken> getPasswordResetTokens() {
		return passwordResetTokens;
	}

	public void setPasswordResetTokens(List<PasswordResetToken> passwordResetTokens) {
		this.passwordResetTokens = passwordResetTokens;
	}

	public List<UserSession> getUserSessions() {
		return userSessions;
	}

	public void setUserSessions(List<UserSession> userSessions) {
		this.userSessions = userSessions;
	}

	public List<MFASecret> getMfaSecrets() {
		return mfaSecrets;
	}

	public void setMfaSecrets(List<MFASecret> mfaSecrets) {
		this.mfaSecrets = mfaSecrets;
	}

	public List<LoginHistory> getLoginHistories() {
		return loginHistories;
	}

	public void setLoginHistories(List<LoginHistory> loginHistories) {
		this.loginHistories = loginHistories;
	}
	
	

}
