package com.example.disasterrecovery.backup;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BackupStratergyFactory {

	@Autowired
	private Map<String, BackupStratergy> strategies;
	
	public BackupStratergy getStratergy(String backupType) {
		BackupStratergy stratergy = strategies.get(backupType
				.toUpperCase());
		if (stratergy == null) {
			throw new RuntimeException(
					"Invalid Backup Type : "+backupType);
		}
		return stratergy;
	}
	
}
