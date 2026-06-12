package com.example.disasterrecovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.disasterrecovery.entity.BackupJob;

@Repository
public interface BackupJobRepository 
		extends JpaRepository<BackupJob, Long>{

	List<BackupJob> findByActive(Boolean active);
}
