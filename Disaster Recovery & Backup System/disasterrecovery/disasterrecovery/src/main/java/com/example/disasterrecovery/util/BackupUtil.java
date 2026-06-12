package com.example.disasterrecovery.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupUtil {
	private BackupUtil() {}
	
	public static String generateBackupName(String prefix) {
		return prefix + "_"+LocalDateTime.now().format(
				DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	}

}
