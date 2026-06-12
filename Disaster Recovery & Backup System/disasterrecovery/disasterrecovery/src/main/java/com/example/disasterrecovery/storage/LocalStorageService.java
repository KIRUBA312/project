package com.example.disasterrecovery.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;

@Service
public class LocalStorageService {

	public String storeFile(
			String sourcePath,String destinationFolder) {
		try {
			Path source = Path.of(sourcePath);
			Path destination = Path.of(destinationFolder,
					source.getFileName().toString());
			Files.copy(source, destination,StandardCopyOption.REPLACE_EXISTING);
			
			return destination.toString();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		
	}
}
