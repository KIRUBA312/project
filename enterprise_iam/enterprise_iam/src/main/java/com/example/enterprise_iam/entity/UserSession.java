package com.example.enterprise_iam.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "user_sessions")
public class UserSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "session_id",length = 255)
	private String sessionId;
	
	@Lob
	@Column(name = "jwt_token")
	private String jwtToken;
	
	@Column(name = "device",length = 255)
	private String device;
	
	@Column(name = "ip_address",length = 100)
	private String ipAddress;
	
	@Column(name = "login_time")
	private LocalDateTime loginTime;
	
	@Column(name = "logout_time")
	private LocalDateTime logoutTime;
	
	@Column(name = "active")
	private Boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public UserSession() {}

	public UserSession(Long id, String sessionId, String jwtToken, String device, String ipAddress,
			LocalDateTime loginTime, LocalDateTime logoutTime, Boolean active, User user) {
		super();
		this.id = id;
		this.sessionId = sessionId;
		this.jwtToken = jwtToken;
		this.device = device;
		this.ipAddress = ipAddress;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.active = active;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalDateTime getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(LocalDateTime logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
