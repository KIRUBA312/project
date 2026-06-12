package com.example.disasterrecovery.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "disaster_simulation")
public class DisasterSimulation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "simulation_type")
	private String simulationType;
	
	@Column(name = "simulation_time")
	private LocalDateTime simulationTime;
	
	@Column(name = "result")
	private String result;
	
	@Column(name = "recovery_time_seconds")
	private Long recoveryTimeSeconds;
	
	public DisasterSimulation() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSimulationType() {
		return simulationType;
	}

	public void setSimulationType(String simulationType) {
		this.simulationType = simulationType;
	}

	public LocalDateTime getSimulationTime() {
		return simulationTime;
	}

	public void setSimulationTime(LocalDateTime simulationTime) {
		this.simulationTime = simulationTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Long getRecoveryTimeSeconds() {
		return recoveryTimeSeconds;
	}

	public void setRecoveryTimeSeconds(Long recoveryTimeSeconds) {
		this.recoveryTimeSeconds = recoveryTimeSeconds;
	}
	
	

}
