package com.example.disasterrecovery.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "backup_jobs")
public class BackupJob {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "job_name")
	private String jobName;
	
	@Column(name = "cron_expression")
	private String cronExpression;
	
	@Column(name = "backup_type")
	private String backupType;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "last_run_time")
	private LocalDateTime lastRunTime;
	
	@Column(name = "next_run_time")
	private LocalDateTime nextRunTime;
	
	public BackupJob() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getBackupType() {
		return backupType;
	}

	public void setBackupType(String backupType) {
		this.backupType = backupType;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getLastRunTime() {
		return lastRunTime;
	}

	public void setLastRunTime(LocalDateTime lastRunTime) {
		this.lastRunTime = lastRunTime;
	}

	public LocalDateTime getNextRunTime() {
		return nextRunTime;
	}

	public void setNextRunTime(LocalDateTime nextRunTime) {
		this.nextRunTime = nextRunTime;
	}
	
	

}
