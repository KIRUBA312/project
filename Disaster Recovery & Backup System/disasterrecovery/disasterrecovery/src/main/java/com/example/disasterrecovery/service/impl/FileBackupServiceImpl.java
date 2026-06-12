package com.example.disasterrecovery.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.disasterrecovery.service.FileBackupService;

@Service
public class FileBackupServiceImpl implements FileBackupService{
	
	@Value("${backup.storage.location}")
	private String backupDirectory;

	@Override
	public String backupFile(String backupLocation) {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(Paths.get(backupDirectory));
			Path source = Paths.get(backupLocation);
			String fileName = source.getFileName().toString();
			Path destination = Paths.get(backupDirectory,
					fileName);
			Files.copy(source, destination,
					StandardCopyOption.REPLACE_EXISTING);
			return destination.toString();
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("File backup failed"
					+e.getMessage());
		}
		
	}

	@Override
	public void restorefile(String backupLocation, 
			String restoreLocation) {
		// TODO Auto-generated method stub
		try {
			Path source = Paths.get(backupLocation);
			Path restoreDir = Paths.get(restoreLocation);
			Files.createDirectories(restoreDir);
			Path destination = restoreDir.resolve(source.getFileName());
			Files.copy(source,destination,
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException("Restore failed"
					+e.getMessage());
		}
		
	}
	
	

}
