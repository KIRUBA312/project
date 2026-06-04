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

import com.example.airbnbbooking.dto.PropertyRequestDto;
import com.example.airbnbbooking.dto.PropertyResponseDto;
import com.example.airbnbbooking.service.PropertyService;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;
	
	@PostMapping
	public ResponseEntity<PropertyResponseDto>createProperty(
			@RequestBody PropertyRequestDto dto){
		
		return ResponseEntity.ok(propertyService.createProperty(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<PropertyResponseDto>> getAllProperties()
	{
		return ResponseEntity.ok(propertyService.getAllProperties());
	}
	@GetMapping("/{id}")
	public ResponseEntity<PropertyResponseDto>getProductById(
			@PathVariable Long id){
		return ResponseEntity.ok(propertyService.getProprtyById(id));
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<PropertyResponseDto> updateProperty(
			@PathVariable Long id,@RequestBody PropertyRequestDto dto){
		return ResponseEntity.ok(
				propertyService.updateProperty(id, dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProperty(
			@PathVariable Long id){
		return ResponseEntity.ok(propertyService.deleteProperty(id));
	}
	@GetMapping("/location/{location}")
	public ResponseEntity<List<PropertyResponseDto>> getPropertiesByLocation(
			@PathVariable String location){
		return ResponseEntity.ok(propertyService
				.getPropertiesByLocation(location));
	}
	
	@GetMapping("/host/{hostId}")
	public ResponseEntity<List<PropertyResponseDto>> getPropertiesByHostId(
			@PathVariable Long hostId){
		return ResponseEntity.ok(propertyService
				.getPropertiesByHostId(hostId));
	}
	
	
}
