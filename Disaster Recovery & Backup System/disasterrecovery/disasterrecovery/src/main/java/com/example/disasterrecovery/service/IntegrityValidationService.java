package com.example.disasterrecovery.service;

public interface IntegrityValidationService {

	String generateChecksum(String backupLocation);

	boolean validateBackupIntegrity(String backupLocation, 
			String checksum);

}
