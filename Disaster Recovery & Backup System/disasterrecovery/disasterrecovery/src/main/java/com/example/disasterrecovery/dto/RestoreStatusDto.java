package com.example.disasterrecovery.dto;

import java.time.LocalDateTime;

public class RestoreStatusDto {

	private Long restoreId;
	private String restoreStatus;
	private LocalDateTime restoreEndTime;

	
	public Long getRestoreId() {
		return restoreId;
	}
	public void setRestoreId(Long restoreId) {
		this.restoreId = restoreId;
	}
	public String getRestoreStatus() {
		return restoreStatus;
	}
	public void setRestoreStatus(String restoreStatus) {
		this.restoreStatus = restoreStatus;
	}
	
	public LocalDateTime getRestoreEndTime() {
		return restoreEndTime;
	}
	public void setRestoreEndTime(LocalDateTime restoreEndTime) {
		this.restoreEndTime = restoreEndTime;
	}
	
	
	
}
