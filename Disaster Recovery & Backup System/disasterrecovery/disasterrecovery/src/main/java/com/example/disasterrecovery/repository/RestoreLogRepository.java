package com.example.disasterrecovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.disasterrecovery.entity.RestoreLog;

@Repository
public interface RestoreLogRepository extends
	JpaRepository<RestoreLog, Long>{

	List<RestoreLog> findByRestoreStatus(String restoreStatus);
}
