package com.example.disasterrecovery.util;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class ChecksumUtil {
	
	private ChecksumUtil() {}
	
	public static String generateChecksum(String filePath) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			FileInputStream fis = new FileInputStream(filePath);
			
			byte[] buffer = new byte[1024];
			int bytesRead;
			while((bytesRead = fis.read(buffer))!=-1) {
				digest.update(buffer,0,bytesRead);
			}
			fis.close();
			byte[] hash = digest.digest();
			StringBuilder sb = new StringBuilder();
			for(byte b : hash) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(
					e.getMessage());
		}
	}

}
