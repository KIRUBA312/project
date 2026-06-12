package com.example.disasterrecovery.dto;

import java.time.LocalDateTime;

public class RestoreResponseDto {

	private Long id;
	private Long backupId;
	private String restoreStatus;
	private LocalDateTime restoreStartTime;
	private LocalDateTime restoreEndTime;
	private String restoreBy;
	private String remarks;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRestoreStatus() {
		return restoreStatus;
	}
	public void setRestoreStatus(String restoreStatus) {
		this.restoreStatus = restoreStatus;
	}
	public LocalDateTime getRestoreStartTime() {
		return restoreStartTime;
	}
	public void setRestoreStartTime(LocalDateTime restoreStartTime) {
		this.restoreStartTime = restoreStartTime;
	}
	public LocalDateTime getRestoreEndTime() {
		return restoreEndTime;
	}
	public void setRestoreEndTime(LocalDateTime restoreEndTime) {
		this.restoreEndTime = restoreEndTime;
	}
	
	public String getRestoreBy() {
		return restoreBy;
	}
	public void setRestoreBy(String restoreBy) {
		this.restoreBy = restoreBy;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void setBackupId(Long backupId) {
		this.backupId = backupId;
	}
	public Long getBackupId() {
		return backupId;
	}
	
	
	
}
