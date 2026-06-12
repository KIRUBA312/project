package com.example.disasterrecovery.backup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("DIFFERENTIAL")
public class DifferentialBackupStratergy implements BackupStratergy{
	
	@Value("${backup.storage.location}")
	private String backupFolder;

	@Override
	public String executeBackup(String sourcePath) {
		// TODO Auto-generated method stub
		try {
			Path source = Path.of(sourcePath);
			String backupFileName = "DIFF_"+System.currentTimeMillis()+
					"_"+source.getFileName();
			Path destination = Path.of(backupFolder,backupFileName);
			Files.copy(source, destination,
					StandardCopyOption.REPLACE_EXISTING);
			return destination.toString();
					
		} catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException(
					"Differential Backup Failed");
		}
	}
	
	

	
}
