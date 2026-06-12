package com.example.disasterrecovery.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.disasterrecovery.dto.BackupRequestDto;
import com.example.disasterrecovery.service.BackupService;

@Component
public class DatabaseBackupScheduler {

	@Autowired
	private BackupService backupService;
	
	@Scheduled(cron = "0 0 1 * * ?")
	public void performDailyBackup() {
		BackupRequestDto dto = new BackupRequestDto();
		dto.setBackupName("Daily-Full-Backup");
		dto.setBackupType("FULL");
		dto.setBackupLocation("E:/Database/sample-db.sql");
		dto.setCompressed(true);
		dto.setEncrypted(true);
		dto.setStorageType("LOCAL");
		backupService.startBackup(dto);
		System.out.println("Daily backup completed");
	}
	
}
