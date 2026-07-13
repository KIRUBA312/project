package com.example.enterprise_iam.dto.response;

public class AdminDashboardResponseDto {

	private Long totalUsers;
	private Long enabledUsers;
	private Long disabledUsers;
	private Long lockedUsers;
	private Long totalRoles;
	private Long totalPermissions;
	private Long activeSessions;
	
	public AdminDashboardResponseDto() {

		// TODO Auto-generated constructor stub
	}

	public Long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public Long getEnabledUsers() {
		return enabledUsers;
	}

	public void setEnabledUsers(Long enabledUsers) {
		this.enabledUsers = enabledUsers;
	}

	public Long getDisabledUsers() {
		return disabledUsers;
	}

	public void setDisabledUsers(Long disabledUsers) {
		this.disabledUsers = disabledUsers;
	}

	public Long getLockedUsers() {
		return lockedUsers;
	}

	public void setLockedUsers(Long lockedUsers) {
		this.lockedUsers = lockedUsers;
	}

	public Long getTotalRoles() {
		return totalRoles;
	}

	public void setTotalRoles(Long totalRoles) {
		this.totalRoles = totalRoles;
	}

	public Long getTotalPermissions() {
		return totalPermissions;
	}

	public void setTotalPermissions(Long totalPermissions) {
		this.totalPermissions = totalPermissions;
	}

	public Long getActiveSessions() {
		return activeSessions;
	}

	public void setActiveSessions(Long activeSessions) {
		this.activeSessions = activeSessions;
	}
	
	
	
}
