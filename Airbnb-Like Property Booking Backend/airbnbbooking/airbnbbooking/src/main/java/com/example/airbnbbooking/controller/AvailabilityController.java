package com.example.airbnbbooking.controller;

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

import com.example.airbnbbooking.dto.AvailabilityRequestDto;
import com.example.airbnbbooking.service.AvailabilityService;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

	@Autowired
	private AvailabilityService availabilityService;
	
	@PostMapping
	public ResponseEntity<AvailabilityRequestDto> createAvailability(
			@RequestBody AvailabilityRequestDto dto){
		return ResponseEntity.ok(availabilityService
				.createAvailability(dto));
	}
	@GetMapping
	public ResponseEntity<List<AvailabilityRequestDto>> getAllAvailability(){
		return ResponseEntity.ok(
				availabilityService.getAllAvailability());
	}
	@GetMapping("/{id}")
	public ResponseEntity<AvailabilityRequestDto> getAvailabilityById(
			@PathVariable Long id){
		return ResponseEntity.ok(availabilityService.getAvailabilityById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AvailabilityRequestDto> updateAvailability(
			@PathVariable Long id,@RequestBody AvailabilityRequestDto dto){
		
		return ResponseEntity.ok(availabilityService.updateAvailability(
				id,dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAvailability(
			@PathVariable Long id){
		return ResponseEntity.ok(availabilityService
				.deleteAvailability(id));
	}
	
	@GetMapping("/property/{propertyId}")
	public ResponseEntity<List<AvailabilityRequestDto>> 
	getAvailabiltyByProperty(
			@PathVariable Long propertyId){
		return ResponseEntity.ok(availabilityService
				.getAvailabilityByProperty(propertyId));
		
	}
	
}
