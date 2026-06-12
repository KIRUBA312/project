package com.example.disasterrecovery.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressionUtil {
	
	private CompressionUtil() {}
	
	public static String compressFile(String sourceFile) {
		try {
			String zipFile = sourceFile + ".zip";
			
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			File file = new File(sourceFile);
			FileInputStream fis = new FileInputStream(file);
			zos.putNextEntry(new ZipEntry(file.getName()));
			byte[] bytes = new byte[1024];
			int length;
			while((length = fis.read(bytes))>=0) {
				zos.write(bytes,0,length);
			}
			zos.closeEntry();
			zos.close();
			fis.close();
			return zipFile;
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
