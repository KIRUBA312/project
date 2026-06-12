package com.example.disasterrecovery.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageFactory {
	@Autowired
	private LocalStorageService localStorageService;
	@Autowired
	private S3StorageService s3StorageService;
	
	public Object getStorageService(String storageType) {
		if ("LOCAL".equalsIgnoreCase(storageType)) {
			
			return localStorageService;
			
		}
		if ("S3".equalsIgnoreCase(storageType)) {
			return s3StorageService;
			
		}
		throw new RuntimeException("Invalid Storage Type");
	}

}
