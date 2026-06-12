package com.example.disasterrecovery.storage;

import org.springframework.stereotype.Service;

@Service
public class S3StorageService {

	public String uploadFile(String filePath) {
		return "Uploaded To AWS S3 : "+filePath;
	}
	
}
