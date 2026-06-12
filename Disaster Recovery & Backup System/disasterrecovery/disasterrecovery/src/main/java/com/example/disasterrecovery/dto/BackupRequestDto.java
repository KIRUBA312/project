package com.example.disasterrecovery.dto;

public class BackupRequestDto {

	private String backupName;
	private String backupType;
	private String backupLocation;
	private Boolean encrypted;
	private Boolean compressed;
	private String storageType;
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
	public String getBackupLocation() {
		return backupLocation;
	}
	public void setBackupLocation(String backupLocation) {
		this.backupLocation = backupLocation;
	}
	public Boolean getEncrypted() {
		return encrypted;
	}
	public void setEncrypted(Boolean encrypted) {
		this.encrypted = encrypted;
	}
	public Boolean getCompressed() {
		return compressed;
	}
	public void setCompressed(Boolean compressed) {
		this.compressed = compressed;
	}
	public String getStorageType() {
		return storageType;
	}
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
	
	
	
}
