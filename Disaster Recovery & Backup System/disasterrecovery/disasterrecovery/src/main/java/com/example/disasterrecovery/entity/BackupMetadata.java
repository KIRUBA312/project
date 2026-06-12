package com.example.disasterrecovery.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "backup_metadata")
public class BackupMetadata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "backup_name")
	private String backupName;
	
	@Column(name = "backup_type")
	private String backupType;
	
	@Column(name = "backup_timestamp")
	private LocalDateTime backupTimestamp;
	
	@Column(name = "backup_location")
	private String backupLocation;
	
	@Column(name = "backup_size")
	private Long backupSize;
	
	@Column(name = "checksum")
	private String checksum;
	
	@Column(name = "encrypted")
	private Boolean encrypted;
	
	@Column(name = "compressed")
	private Boolean compressed;
	
	@Column(name = "storage_type")
	private String storageType;
	
	@Column(name = "status")
	private String status;
	
	public BackupMetadata() {}

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

	public Long getBackupSize() {
		return backupSize;
	}

	public void setBackupSize(Long backupSize) {
		this.backupSize = backupSize;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
