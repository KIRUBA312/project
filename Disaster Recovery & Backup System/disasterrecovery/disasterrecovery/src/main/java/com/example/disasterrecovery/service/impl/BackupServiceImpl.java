package com.example.disasterrecovery.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.disasterrecovery.backup.BackupStratergyFactory;
import com.example.disasterrecovery.dto.BackupRequestDto;
import com.example.disasterrecovery.dto.BackupResponseDto;
import com.example.disasterrecovery.dto.BackupStatusDto;
import com.example.disasterrecovery.entity.BackupMetadata;
import com.example.disasterrecovery.exception.BackupFailedException;
import com.example.disasterrecovery.repository.BackupMetadataRepository;
import com.example.disasterrecovery.service.BackupService;
import com.example.disasterrecovery.service.FileBackupService;
import com.example.disasterrecovery.service.IntegrityValidationService;
import com.example.disasterrecovery.storage.LocalStorageService;
import com.example.disasterrecovery.storage.StorageFactory;
import com.example.disasterrecovery.util.ChecksumUtil;
import com.example.disasterrecovery.util.CompressionUtil;
import com.example.disasterrecovery.util.EncryptionUtil;

@Service
public class BackupServiceImpl implements BackupService{
	
	@Autowired
	private BackupMetadataRepository backupMetadataRepository;
	@Autowired
	private FileBackupService fileBackupService;
	@Autowired
	private IntegrityValidationService integrityValidationService;
	@Autowired
	private BackupStratergyFactory backupStratergyFactory;
	@Autowired
	private StorageFactory storageFactory;
	

	@Override
	public BackupResponseDto startBackup(BackupRequestDto dto) {
		// TODO Auto-generated method stub
		
		try {
			String backupLocation;
			
			if("LOCAL".equalsIgnoreCase(dto.getStorageType())) {
				LocalStorageService storageService = 
						(LocalStorageService) storageFactory
						.getStorageService(dto.getStorageType());
				backupLocation =
						storageService.storeFile(dto.getBackupLocation(),
								"E:/downloadsE/profile/Stackly_office/JAVA & MYSQL notes/Task/Disaster Recovery & Backup System/backups");
			}else {
				backupLocation= dto.getBackupLocation();
			}
			if(Boolean.TRUE.equals(dto.getCompressed())) {
				backupLocation=CompressionUtil.compressFile(backupLocation);
			}
			if(Boolean.TRUE.equals(dto.getEncrypted())) {
				EncryptionUtil.encryptFile(backupLocation);
			}
			
			String checksum = integrityValidationService
				.generateChecksum(backupLocation);
			File file = new File(backupLocation);
			long filesize = file.length();
			
			BackupMetadata backup = new BackupMetadata();
			
			backup.setBackupName(dto.getBackupName());
			backup.setBackupType(dto.getBackupType());
			backup.setBackupLocation(backupLocation);
			backup.setEncrypted(dto.getEncrypted());
			backup.setCompressed(dto.getCompressed());
			backup.setStorageType(dto.getStorageType());
			
			backup.setBackupTimestamp(
					LocalDateTime.now());
			backup.setStatus("COMPLETED");
			backup.setBackupSize(filesize);
			backup.setChecksum(checksum);
			backup = backupMetadataRepository.save(backup);
			
			return maptoresponse(backup);
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new BackupFailedException("Backup creation failed : "
					+e.getMessage());
		}
		
	}

	@Override
	public BackupStatusDto getBackupStatus(Long id) {
		// TODO Auto-generated method stub
		BackupMetadata backup = backupMetadataRepository.findById(id)
				.orElseThrow(() ->new BackupFailedException(
						"Backup not found"));
		BackupStatusDto dto = new BackupStatusDto();
		
		dto.setBackupId(backup.getId());
		dto.setStatus(backup.getStatus());
		dto.setBackupLocation(backup.getBackupLocation());
		
		return dto;
	}

	@Override
	public List<BackupResponseDto> getAllBackups() {
		// TODO Auto-generated method stub
		return backupMetadataRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}

	@Override
	public BackupResponseDto getBackupById(Long id) {
		// TODO Auto-generated method stub
		BackupMetadata backup = backupMetadataRepository.findById(id)
				.orElseThrow(() ->new BackupFailedException(
						"Backup not found"));
		return maptoresponse(backup);
	}

	@Override
	public BackupResponseDto updateBackup(Long id, 
			BackupRequestDto dto) {
		// TODO Auto-generated method stub
		BackupMetadata backup = backupMetadataRepository.findById(id)
				.orElseThrow(() -> new BackupFailedException(
						"Backup not found"));
		backup.setBackupName(dto.getBackupName());
		backup.setBackupType(dto.getBackupType());
		backup.setBackupLocation(dto.getBackupLocation());
		backup.setEncrypted(dto.getEncrypted());
		backup.setCompressed(dto.getCompressed());
		backup.setStorageType(dto.getStorageType());
		backup = backupMetadataRepository.save(backup);
		return maptoresponse(backup);
	}

	@Override
	public String deleteBackup(Long id) {
		// TODO Auto-generated method stub
		BackupMetadata backup = backupMetadataRepository.findById(id)
				.orElseThrow(() -> new BackupFailedException(
						"Backup not found"));
		backupMetadataRepository.delete(backup);
		return "Backup deleted successfully";
	}
	
	private BackupResponseDto maptoresponse(BackupMetadata backup) {
		BackupResponseDto dto = new BackupResponseDto();
		dto.setId(backup.getId());
		dto.setBackupName(backup.getBackupName());
		dto.setBackupType(backup.getBackupType());
		dto.setBackupTimestamp(backup.getBackupTimestamp());
		dto.setBackupLocation(backup.getBackupLocation());
		dto.setStatus(backup.getStatus());
		return dto;
	}
	
	

}
