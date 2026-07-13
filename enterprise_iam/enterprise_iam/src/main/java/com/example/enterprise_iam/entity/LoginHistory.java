package com.example.enterprise_iam.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "login_history")
public class LoginHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "login_time")
	private LocalDateTime loginTime;
	
	@Column(name = "status",length = 30)
	private String status;
	
	@Column(name = "ip_address",length = 100)
	private String ipAddress;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public LoginHistory() {}

	public LoginHistory(Long id, LocalDateTime loginTime, String status, String ipAddress, User user) {
		super();
		this.id = id;
		this.loginTime = loginTime;
		this.status = status;
		this.ipAddress = ipAddress;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
