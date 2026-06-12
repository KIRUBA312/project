package com.example.disasterrecovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.disasterrecovery.entity.BackupMetadata;

@Repository
public interface BackupMetadataRepository extends 
		JpaRepository<BackupMetadata, Long>{
	
	List<BackupMetadata> findByStatus(String status);
	List<BackupMetadata> findByBackupType(String backupType);

}
