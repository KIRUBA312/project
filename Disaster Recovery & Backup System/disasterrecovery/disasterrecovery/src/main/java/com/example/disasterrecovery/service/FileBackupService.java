package com.example.disasterrecovery.service;

public interface FileBackupService {

	String backupFile(String backupLocation);

	 void restorefile(String backupLocation, 
			String restoreLocation);

}
