package com.example.disasterrecovery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.disasterrecovery.entity.DisasterSimulation;
import com.example.disasterrecovery.service.DisasterRecoveryService;

@RestController
@RequestMapping("/api/simulation")
public class DisasterSimulationController {

	@Autowired
	private DisasterRecoveryService service;
	
	@PostMapping
	public ResponseEntity<DisasterSimulation> createSimulation(
			@RequestBody DisasterSimulation simulation){
		return ResponseEntity.ok(service.createSimulation(simulation));
	}
	@GetMapping
	public ResponseEntity<List<DisasterSimulation>>
	getAllSimulations(){
		return ResponseEntity.ok(service.getAllSimulations());
	}
	@GetMapping("/{id}")
	public ResponseEntity<DisasterSimulation> getSimulationById(
			@PathVariable Long id){
		return ResponseEntity.ok(service.getSimulationById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<DisasterSimulation> updateSimulation(
			@PathVariable Long id,
			@RequestBody DisasterSimulation simulation){
		return ResponseEntity.ok(service.updateSimulation(id,simulation));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSimulation(@PathVariable Long id){
		return ResponseEntity.ok(service.deleteSimulation(id));
	}
	@PostMapping("/db-crash")
	public ResponseEntity<DisasterSimulation> simulateDatabaseCrash(){
		return ResponseEntity.ok(service.simulateDatabaseCrash());
	}
	@PostMapping("/file-loss")
	public ResponseEntity<DisasterSimulation> simulateFileLoss(){
		return ResponseEntity.ok(service.simulateFileLoss());
	}
	@PostMapping("/server-failure")
	public ResponseEntity<DisasterSimulation> simulateServerFailure(){
		return ResponseEntity.ok(service.simulateServerFailure());
	}
	
	
}
