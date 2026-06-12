package com.example.disasterrecovery.dto;

public class RestoreRequestDto {

	private Long backupId;
	private String restoreLocation;
	private String restoredBy;
	private String remarks;
	
	public Long getBackupId() {
		return backupId;
	}
	public void setBackupId(Long backupId) {
		this.backupId = backupId;
	}
	public String getRestoredBy() {
		return restoredBy;
	}
	public void setRestoredBy(String restoredBy) {
		this.restoredBy = restoredBy;
	}
	public String getRemarks() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRestoreLocation() {
		return restoreLocation;
	}
	public void setRestoreLocation(String restoreLocation) {
		this.restoreLocation = restoreLocation;
	}
	
	
	
	
}
