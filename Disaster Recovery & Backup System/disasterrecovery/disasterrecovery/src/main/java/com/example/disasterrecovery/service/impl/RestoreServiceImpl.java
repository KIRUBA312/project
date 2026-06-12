package com.example.disasterrecovery.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.disasterrecovery.dto.RestoreRequestDto;
import com.example.disasterrecovery.dto.RestoreResponseDto;
import com.example.disasterrecovery.dto.RestoreStatusDto;
import com.example.disasterrecovery.entity.BackupMetadata;
import com.example.disasterrecovery.entity.RestoreLog;
import com.example.disasterrecovery.exception.IntegrityCheckException;
import com.example.disasterrecovery.exception.RestoreFailedException;
import com.example.disasterrecovery.repository.BackupMetadataRepository;
import com.example.disasterrecovery.repository.RestoreLogRepository;
import com.example.disasterrecovery.service.FileBackupService;
import com.example.disasterrecovery.service.IntegrityValidationService;
import com.example.disasterrecovery.service.RestoreService;

@Service
public class RestoreServiceImpl implements RestoreService{

	@Autowired
	private RestoreLogRepository restoreLogRepository;
	@Autowired
	private BackupMetadataRepository backupMetadataRepository;
	@Autowired
	private FileBackupService fileBackupService;
	@Autowired
	private IntegrityValidationService integrityValidationService;
	
	@Override
	public RestoreResponseDto restoreBackup(RestoreRequestDto dto) {
		// TODO Auto-generated method stub
		BackupMetadata backup = backupMetadataRepository.findById(
				dto.getBackupId()).orElseThrow(() ->
				new RestoreFailedException("Backup not found"));
		boolean valid = integrityValidationService
				.validateBackupIntegrity(
				backup.getBackupLocation(),
				backup.getChecksum());
		if(!valid) {
			throw new IntegrityCheckException(
					"Backup file is corrupted");
		}
		fileBackupService.restorefile(
				backup.getBackupLocation(),dto.getRestoreLocation());
		RestoreLog restoreLog = new RestoreLog();
		restoreLog.setBackupMetadata(backup);
		restoreLog.setRestoreStartTime(LocalDateTime.now());
		restoreLog.setRestoreEndTime(LocalDateTime.now());
		restoreLog.setRestoreStatus("COMPLETED");
		restoreLog.setRestoreBy(dto.getRestoredBy());
		restoreLog.setRemarks(dto.getRemarks());
		restoreLog = restoreLogRepository.save(restoreLog);
		return maptoresponse(restoreLog);
	}
	@Override
	public RestoreStatusDto getRestoreStatus(Long id) {
		// TODO Auto-generated method stub
		RestoreLog restoreLog = restoreLogRepository.findById(id)
				.orElseThrow(() ->new RestoreFailedException(
						"Restore log not found"));
		RestoreStatusDto dto = new RestoreStatusDto();
		dto.setRestoreId(restoreLog.getId());
		dto.setRestoreStatus(restoreLog.getRestoreStatus());
		dto.setRestoreEndTime(restoreLog.getRestoreEndTime());
		return dto;
	}
	@Override
	public List<RestoreResponseDto> getAllRestoreLogs() {
		// TODO Auto-generated method stub
		return restoreLogRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public RestoreResponseDto getRestoreById(Long id) {
		// TODO Auto-generated method stub
		RestoreLog restoreLog = restoreLogRepository.findById(id)
				.orElseThrow(() ->new RestoreFailedException(
						"Restore log not found"));
		return maptoresponse(restoreLog);
	}
	@Override
	public RestoreResponseDto updateRestore(Long id, RestoreRequestDto dto) {
		// TODO Auto-generated method stub
		RestoreLog restoreLog = restoreLogRepository.findById(id)
				.orElseThrow(() ->new RestoreFailedException(
						"Restore log not found"));
		BackupMetadata backup = backupMetadataRepository.findById(
				dto.getBackupId()).orElseThrow(() ->
				new RestoreFailedException("Backup not found"));
		restoreLog.setBackupMetadata(backup);
		restoreLog.setRestoreBy(dto.getRestoredBy());
		restoreLog.setRemarks(dto.getRemarks());
		restoreLog = restoreLogRepository.save(restoreLog);
		return maptoresponse(restoreLog);
	}
	@Override
	public String deleteRestoreLog(Long id) {
		// TODO Auto-generated method stub
		RestoreLog restoreLog = restoreLogRepository.findById(id)
				.orElseThrow(() ->new RestoreFailedException(
						"Restore log not found"));
		restoreLogRepository.delete(restoreLog);
		return "Restore log deleted successfully";
	}
	private RestoreResponseDto maptoresponse(RestoreLog restoreLog) {
		RestoreResponseDto dto = new RestoreResponseDto();
		dto.setId(restoreLog.getId());
		dto.setBackupId(restoreLog.getBackupMetadata()
				.getId());
		dto.setRestoreStatus(restoreLog.getRestoreStatus());
		dto.setRestoreStartTime(restoreLog.getRestoreStartTime());
		dto.setRestoreEndTime(restoreLog.getRestoreEndTime());
		dto.setRestoreBy(restoreLog.getRestoreBy());
		dto.setRemarks(restoreLog.getRemarks());
		
		return dto;
	}
	
	
	
}
