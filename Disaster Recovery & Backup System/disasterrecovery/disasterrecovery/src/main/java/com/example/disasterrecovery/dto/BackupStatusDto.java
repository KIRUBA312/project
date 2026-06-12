package com.example.disasterrecovery.dto;

public class BackupStatusDto {

	private Long backupId;
	private String status;
	private String backupLocation;
	
	public Long getBackupId() {
		return backupId;
	}
	public void setBackupId(Long backupId) {
		this.backupId = backupId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBackupLocation() {
		return backupLocation;
	}
	public void setBackupLocation(String backupLocation) {
		this.backupLocation = backupLocation;
	}
	
	
	
}
