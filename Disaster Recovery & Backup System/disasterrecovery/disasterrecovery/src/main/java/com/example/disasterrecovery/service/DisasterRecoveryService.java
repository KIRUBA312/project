package com.example.disasterrecovery.service;

import java.util.List;


import com.example.disasterrecovery.entity.DisasterSimulation;

public interface DisasterRecoveryService {

	DisasterSimulation createSimulation(DisasterSimulation simulation);

	List<DisasterSimulation> getAllSimulations();

	DisasterSimulation getSimulationById(Long id);

	DisasterSimulation updateSimulation(Long id, 
			DisasterSimulation simulation);

	String deleteSimulation(Long id);

	DisasterSimulation simulateDatabaseCrash();

	DisasterSimulation simulateFileLoss();

	DisasterSimulation simulateServerFailure();
	

}
