package com.example.disasterrecovery.service;

import java.util.List;


import com.example.disasterrecovery.dto.BackupRequestDto;
import com.example.disasterrecovery.dto.BackupResponseDto;
import com.example.disasterrecovery.dto.BackupStatusDto;

public interface BackupService {

	BackupResponseDto startBackup(BackupRequestDto dto);

	BackupStatusDto getBackupStatus(Long id);

	List<BackupResponseDto> getAllBackups();

	BackupResponseDto getBackupById(Long id);

	BackupResponseDto updateBackup(Long id, BackupRequestDto dto);

	String deleteBackup(Long id);

}
