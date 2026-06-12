package com.example.disasterrecovery.util;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
	
	private static final String SECRET_KEY ="1234567890123456";

	private EncryptionUtil() {}
	
	public static void encryptFile(
			String filePath) {
		try {
			SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), 
					"AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
			byte[] encrypted = cipher.doFinal(fileContent);
			Files.write(Paths.get(filePath), encrypted);
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
