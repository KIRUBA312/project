package com.example.disasterrecovery.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "restore_logs")
public class RestoreLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "backup_id")
	private BackupMetadata backupMetadata;
	
	@Column(name = "restore_start_time")
	private LocalDateTime restoreStartTime;
	
	@Column(name = "restore_end_time")
	private LocalDateTime restoreEndTime;
	
	@Column(name = "restore_status")
	private String restoreStatus;
	
	@Column(name = "restore_by")
	private String restoreBy;
	
	@Column(name = "remarks")
	private String remarks;
	
	public RestoreLog() {}

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

	public String getRestoreStatus() {
		return restoreStatus;
	}

	public void setRestoreStatus(String restoreStatus) {
		this.restoreStatus = restoreStatus;
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
	
	

}
