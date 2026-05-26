package com.example.taskmanagement.dto;

public class DashboardResponseDto {

	private Long totalTasks;
	private Long completedTasks;
	private Long pendingTasks;
	private Long blockedTasks;
	
	public DashboardResponseDto() {}

	public Long getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(Long totalTasks) {
		this.totalTasks = totalTasks;
	}

	public Long getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(Long completedTasks) {
		this.completedTasks = completedTasks;
	}

	public Long getPendingTasks() {
		return pendingTasks;
	}

	public void setPendingTasks(Long pendingTasks) {
		this.pendingTasks = pendingTasks;
	}

	public Long getBlockedTasks() {
		return blockedTasks;
	}

	public void setBlockedTasks(Long blockedTasks) {
		this.blockedTasks = blockedTasks;
	}
	
	
	
}
