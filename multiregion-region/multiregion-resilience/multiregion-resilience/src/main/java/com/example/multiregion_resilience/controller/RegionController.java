package com.example.multiregion_resilience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.multiregion_resilience.dto.PageResponse;
import com.example.multiregion_resilience.dto.RegionRequest;
import com.example.multiregion_resilience.dto.RegionResponse;
import com.example.multiregion_resilience.dto.RegionStatusRequest;
import com.example.multiregion_resilience.enums.RegionStatus;
import com.example.multiregion_resilience.service.RegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/regions")
@Tag(name = "Region Management ",
		description = "APIs for managing multi-region deployments")
public class RegionController {
	
	@Autowired
	private RegionService regionService;
	
	@PostMapping
	@Operation(summary = "Create a new region")
	@ApiResponses(
			{
        @ApiResponse(
                responseCode = "201",
                description = "Region created successfully"
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Region already exists"
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Invalid request"
        )
	})
	public ResponseEntity<RegionResponse> createRegion(
			@Valid @RequestBody RegionRequest request){
		
		RegionResponse response = regionService.createRegion(
				request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RegionResponse> getRegionById(
			@PathVariable Long id){
		return ResponseEntity.ok(regionService.getRegionById(id));
	}
	@GetMapping("/code/{regionCode}")
	public ResponseEntity<RegionResponse> getRegionByCode(
			@PathVariable String Regioncode){
		return ResponseEntity.ok(regionService
				.getRegionByCode(Regioncode));
	}
	
	@GetMapping
	public ResponseEntity<PageResponse<RegionResponse>> getAllRegions(
			@Parameter(
					description = "Zero-based page number"
			)
			@RequestParam(defaultValue = "0")
			int page,
			@Parameter(
					description = "Number of records per page"
			)
			@RequestParam(defaultValue = "10")
			int size,
			@Parameter(
					description = "Filter by region status"
					)
			@RequestParam(required = false)
			RegionStatus status,
			@Parameter(description = "Filter by enabled status")
			@RequestParam(required = false)
			Boolean enabled
		){
		return ResponseEntity.ok(regionService.getAllRegions(
				page,size,status,enabled)
				);
	}
	@PutMapping("/{id}")
	public ResponseEntity<RegionResponse> updateRegion(
			@PathVariable Long id,@Valid @RequestBody RegionRequest request)
	{
		return ResponseEntity.ok(regionService.updateRegion(
				id,request));
	}
	@PutMapping("/{id}/status")
	public ResponseEntity<RegionResponse> updateRegionStatus(
			@PathVariable Long id,@Valid 
			@RequestBody RegionStatusRequest request)
	{
		return ResponseEntity.ok(regionService
				.updateRegionStatus(id,request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRegion(
			@PathVariable Long id){
		regionService.deleteRegion(id);
		return ResponseEntity.noContent().build();
	}

}
