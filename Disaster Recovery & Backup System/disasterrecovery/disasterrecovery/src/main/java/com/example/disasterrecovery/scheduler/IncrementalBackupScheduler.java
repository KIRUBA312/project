package com.example.disasterrecovery.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.disasterrecovery.dto.BackupRequestDto;
import com.example.disasterrecovery.service.BackupService;

@Component
public class IncrementalBackupScheduler {

	@Autowired
	private BackupService backupService;
	
	@Scheduled(cron = "0 0 */6 * * ?")
	public void performIncrementalBackup() {
		BackupRequestDto dto = new BackupRequestDto();
		dto.setBackupName("Incremental-Backup");
		dto.setBackupType("INCREMENTAL");
		dto.setBackupLocation("E:/Database/incremental.sql");
		dto.setCompressed(true);
		dto.setEncrypted(false);
		dto.setStorageType("LOCAL");
		backupService.startBackup(dto);
		System.out.println("Incremental backup completed");
		
	}
	
}
