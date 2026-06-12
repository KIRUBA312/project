package com.example.disasterrecovery.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.disasterrecovery.entity.BackupMetadata;
import com.example.disasterrecovery.repository.BackupMetadataRepository;
import com.example.disasterrecovery.service.RetentionPolicyService;

@Service
public class RetentionPolicyServiceImpl implements RetentionPolicyService{

	@Autowired
	private BackupMetadataRepository backupMetadataRepository;
	
	@Value("${backup.retention.days}")
	private int retentionDays;

	@Override
	public long getExpiredBackupCount() {
		// TODO Auto-generated method stub
		LocalDateTime cutoffDate = LocalDateTime.now()
				.minusDays(retentionDays);
		
		return backupMetadataRepository.findAll().stream()
				.filter(backup ->
				backup.getBackupTimestamp() != null &&
				backup.getBackupTimestamp().isBefore(cutoffDate))
				.count();
	}

	@Override
	public long deleteExpiredBackups() {
		// TODO Auto-generated method stub
		LocalDateTime cutoffDate = LocalDateTime.now()
				.minusDays(retentionDays);
		List<BackupMetadata> backups = 
				backupMetadataRepository.findAll();
		long deletedCount = 0;
		for(BackupMetadata backup:backups) {
			
			try {
				if(backup.getBackupLocation() != null 
						&&
						backup.getBackupTimestamp().isBefore(cutoffDate)) {
					if(backup.getBackupLocation()!=null) {
						File file = new File(
								backup.getBackupLocation());
						
					if (file.exists()) {
						boolean delted =
						file.delete();
						if (!delted) {
							System.out.println(
									"Unable to delete file : "
									+file.getAbsolutePath());
						}
					}
				}
					
				backupMetadataRepository.delete(backup);
				deletedCount++;
				}
			}
			catch(Exception ex) {
				System.out.println("Retention Cleanup Failed : "
						+ ex.getMessage());
			}
		}
		
		return deletedCount;
	}

	@Override
	public String executeRetentionPolicy() {
		// TODO Auto-generated method stub
		long deletedCount = deleteExpiredBackups();
		return deletedCount + "expired backups deleted successfully";
	}
	
	@Scheduled(cron = "0 0 2 * * ?")
	public void scheduledCleanup() {
		long deleted =
		deleteExpiredBackups();
		System.out.println("Retention policy Executed SuccessFully");
		System.out.println("Deleted Backups : "+deleted);
	}
	
	
}
