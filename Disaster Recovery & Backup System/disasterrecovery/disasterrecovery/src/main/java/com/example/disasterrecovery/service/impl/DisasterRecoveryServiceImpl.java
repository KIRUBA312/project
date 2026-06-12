package com.example.disasterrecovery.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.disasterrecovery.entity.DisasterSimulation;
import com.example.disasterrecovery.exception.RestoreFailedException;
import com.example.disasterrecovery.repository.DisasterSimulationRepository;
import com.example.disasterrecovery.service.DisasterRecoveryService;

@Service
public class DisasterRecoveryServiceImpl implements DisasterRecoveryService{

	@Autowired
	private DisasterSimulationRepository repository;

	@Override
	public DisasterSimulation createSimulation(
			DisasterSimulation simulation) {
		// TODO Auto-generated method stub
		simulation.setSimulationTime(LocalDateTime.now());
		return repository.save(simulation);
	}

	@Override
	public List<DisasterSimulation> getAllSimulations() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public DisasterSimulation getSimulationById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElseThrow(() ->
		new RestoreFailedException("Simulation not found"));
	}

	@Override
	public DisasterSimulation updateSimulation(Long id, 
			DisasterSimulation simulation) {
		// TODO Auto-generated method stub
		DisasterSimulation existing = repository.findById(id)
				.orElseThrow(() ->new RestoreFailedException(
						"Simulation not found"));
		existing.setSimulationType(simulation.getSimulationType());
		existing.setResult(simulation.getResult());
		existing.setRecoveryTimeSeconds(simulation
				.getRecoveryTimeSeconds());
		
		return repository.save(existing);
	}

	@Override
	public String deleteSimulation(Long id) {
		// TODO Auto-generated method stub
		DisasterSimulation existing = repository.findById(id)
				.orElseThrow(() -> new RestoreFailedException(
						"Simulation not found"));
		repository.delete(existing);
		return "Simulation deleted successfully";
	}

	@Override
	public DisasterSimulation simulateDatabaseCrash() {
		// TODO Auto-generated method stub
		DisasterSimulation simulation = new DisasterSimulation();
		simulation.setSimulationType("DATABASE_CRASH");
		simulation.setSimulationTime(LocalDateTime.now());
		simulation.setResult("SUCCESS");
		simulation.setRecoveryTimeSeconds(45L);
		
		return repository.save(simulation);
	}

	@Override
	public DisasterSimulation simulateFileLoss() {
		// TODO Auto-generated method stub
		DisasterSimulation simulation = new DisasterSimulation();
		simulation.setSimulationType("FILE_LOSS");
		simulation.setSimulationTime(
				LocalDateTime.now());
		simulation.setResult("SUCCESS");
		simulation.setRecoveryTimeSeconds(30L);
		
		return repository.save(simulation);
	}

	@Override
	public DisasterSimulation simulateServerFailure() {
		// TODO Auto-generated method stub
		DisasterSimulation simulation = new DisasterSimulation();
		simulation.setSimulationType("SERVER_FAILURE");
		simulation.setSimulationTime(LocalDateTime.now());
		simulation.setResult("SUCCESS");
		simulation.setRecoveryTimeSeconds(90L);
		
		return repository.save(simulation);
	}
	
	
	
}
