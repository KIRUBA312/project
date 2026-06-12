package com.example.disasterrecovery.backup;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("INCREMENTAL")
public class IncrementalBackupStratergy implements BackupStratergy{

	@Value("${backup.storage.location}")
	private String backupFolder;

	@Override
	public String executeBackup(String sourcePath) {
		// TODO Auto-generated method stub
		try {
			
			Path source = Path.of(sourcePath);
			String backupFilename = "INC_"+
			System.currentTimeMillis()+"_"+source.getFileName();
			Path destination = Path.of(backupFolder,
					backupFilename);
			Files.copy(source, destination,
					StandardCopyOption.REPLACE_EXISTING);
			return destination.toString();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(
					"Incremental Backup Failed : "
					+ e.getMessage());
		}
		
	}
	
	
}
