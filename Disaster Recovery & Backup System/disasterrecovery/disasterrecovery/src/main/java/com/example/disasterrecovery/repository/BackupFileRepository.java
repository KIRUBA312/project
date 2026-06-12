package com.example.disasterrecovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.disasterrecovery.entity.BackupFile;

@Repository
public interface BackupFileRepository extends 
		JpaRepository<BackupFile, Long>{

	List<BackupFile> findByBackupMetadataId(Long backupId);
}
