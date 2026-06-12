package com.example.disasterrecovery.service.impl;

import java.io.FileInputStream;
import java.security.MessageDigest;

import org.springframework.stereotype.Service;

import com.example.disasterrecovery.service.IntegrityValidationService;


@Service
public class IntegrityValidationServiceImpl implements 
		IntegrityValidationService{

	@Override
	    public String generateChecksum(String backupLocation) {
		// TODO Auto-generated method stub
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				FileInputStream fis = new FileInputStream(backupLocation);
				byte[] buffer = new byte[1024];
				int bytesRead;
				while((bytesRead = fis.read(buffer))!=-1) {
					md.update(buffer,0,bytesRead);
				}
				fis.close();
				byte[] hash = md.digest();
				StringBuilder sb = new StringBuilder();
				
				for(byte b:hash) {
					sb.append(String.format("%02x", b));
				}
				return sb.toString();
				
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException(
						"Checksum generation failed : "+e.getMessage());
			}
	    }

	    @Override
	    public boolean validateBackupIntegrity(String backupLocation, 
	    		String checksum) {
		// TODO Auto-generated method stub
	    	String currentChecksum = generateChecksum(backupLocation);
	    	return currentChecksum.equals(checksum);
	    }
	
	

}
