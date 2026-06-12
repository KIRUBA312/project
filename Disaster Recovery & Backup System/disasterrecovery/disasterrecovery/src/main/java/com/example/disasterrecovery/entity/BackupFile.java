package com.example.disasterrecovery.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "backup_files")
public class BackupFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "backup_id")
	private BackupMetadata backupMetadata;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "original_location")
	private String originalLocation;
	
	@Column(name = "backup_location")
	private String backupLocation;
	
	@Column(name = "checksum")
	private String checksum;
	
	@Column(name = "file_size")
	private Long fileSize;
	
	public BackupFile() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BackupMetadata getBackupMetadata() {
		return backupMetadata;
	}

	public void setBackupMetadata(BackupMetadata backupMetadata) {
		this.backupMetadata = backupMetadata;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalLocation() {
		return originalLocation;
	}

	public void setOriginalLocation(String originalLocation) {
		this.originalLocation = originalLocation;
	}

	public String getBackupLocation() {
		return backupLocation;
	}

	public void setBackupLocation(String backupLocation) {
		this.backupLocation = backupLocation;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	
	
}
