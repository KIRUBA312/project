package com.example.disasterrecovery.dto;

import java.time.LocalDateTime;

public class BackupResponseDto {

	private Long id;
	private String backupName;
	private String backupType;
	private LocalDateTime backupTimestamp;
	private String backupLocation;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBackupName() {
		return backupName;
	}
	public void setBackupName(String backupName) {
		this.backupName = backupName;
	}
	public String getBackupType() {
		return backupType;
	}
	public void setBackupType(String backupType) {
		this.backupType = backupType;
	}
	public LocalDateTime getBackupTimestamp() {
		return backupTimestamp;
	}
	public void setBackupTimestamp(LocalDateTime backupTimestamp) {
		this.backupTimestamp = backupTimestamp;
	}
	
	
	public String getBackupLocation() {
		return backupLocation;
	}
	public void setBackupLocation(String backupLocation) {
		this.backupLocation = backupLocation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
