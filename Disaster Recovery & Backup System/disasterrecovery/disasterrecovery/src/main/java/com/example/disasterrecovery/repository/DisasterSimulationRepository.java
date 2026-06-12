package com.example.disasterrecovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.disasterrecovery.entity.DisasterSimulation;

@Repository
public interface DisasterSimulationRepository extends
	JpaRepository<DisasterSimulation, Long>{

	List<DisasterSimulation> findBySimulationType(String simulationType);
}
