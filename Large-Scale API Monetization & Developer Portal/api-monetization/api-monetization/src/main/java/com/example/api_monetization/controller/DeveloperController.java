package com.example.api_monetization.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.api_monetization.dto.developer.ApiKeyResponse;
import com.example.api_monetization.dto.developer.ConsumerApplicationRequest;
import com.example.api_monetization.dto.developer.ConsumerApplicationResponse;
import com.example.api_monetization.dto.developer.DeveloperRequest;
import com.example.api_monetization.dto.developer.DeveloperResponse;
import com.example.api_monetization.service.DeveloperService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/developers")
@RequiredArgsConstructor
public class DeveloperController {

	@Autowired
	private DeveloperService developerService;
	
	@PostMapping("/{userId}")
	public ResponseEntity<DeveloperResponse> createDeveloper(
			@PathVariable Long userId,@Valid
			@RequestBody DeveloperRequest request){
		DeveloperResponse response = developerService
				.createDeveloper(userId,request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	@PutMapping("/{developerId}")
	public ResponseEntity<DeveloperResponse> updateDeveloper(
			@PathVariable Long developerId,@Valid
			@RequestBody DeveloperRequest request){
		return ResponseEntity.ok(
				developerService.updateDeveloper(developerId, request));
	}
	@GetMapping("/{developerId}")
	public ResponseEntity<DeveloperResponse> getDeveloper(
			@PathVariable Long developerId){
		return ResponseEntity.ok(
				developerService.getDeveloper(developerId));
	}
	@GetMapping
	public ResponseEntity<List<DeveloperResponse>> getAllDevelopers(){
		return ResponseEntity.ok(developerService.getDevelopers());
	}
	
	@DeleteMapping("/{developerId}")
	public ResponseEntity<Void> deleteDeveloper(@PathVariable
			Long developerId){
		developerService.deleteDeveloper(developerId);
		
		return ResponseEntity.noContent().build();
	}
	
	//consumer applications
	
	@PostMapping("/{developerId}/applications")
	public ResponseEntity<ConsumerApplicationResponse> createApplication(
			@PathVariable Long developerId, @Valid
			@RequestBody ConsumerApplicationRequest request){
		ConsumerApplicationResponse response = 
				developerService.createApplication(developerId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@PutMapping("/applications/{applicationId}")
	public ResponseEntity<ConsumerApplicationResponse> updateApplication(
			@PathVariable Long applicationId, @Valid
			@RequestBody ConsumerApplicationRequest request){
		
		return ResponseEntity.ok(
				developerService.updateApplication(applicationId, request));
	}
	@GetMapping("/{developerId}/applications")
	public ResponseEntity<List<ConsumerApplicationResponse>> getApplications(
			@PathVariable Long developerId){
		return ResponseEntity.ok(
				developerService.getApplications(developerId));
	}
	@GetMapping("/applications/{applicationId}")
	public ResponseEntity<ConsumerApplicationResponse> getApplication(
			@PathVariable Long applicationId){
		return ResponseEntity.ok(
				developerService.getApplication(applicationId));
	}
	@DeleteMapping("/applications/{applicationId}")
	public ResponseEntity<Void> deleteApplication(
			@PathVariable Long applicationId){
		developerService.deleteApplication(applicationId);
		return ResponseEntity.noContent().build();
	}
	//API Keys
	
	@PostMapping("/applications/{applicationId}/api-keys")
	public ResponseEntity<ApiKeyResponse> generateApiKey(
			@PathVariable Long applicationId){
		ApiKeyResponse response = developerService
				.generateApiKey(applicationId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/api-keys/{apiKeyId}/regenerate")
	public ResponseEntity<ApiKeyResponse> regeneratedApiKey(
			@PathVariable Long apiKeyId){
		return ResponseEntity.ok(developerService
				.regenerateApiKey(apiKeyId));
	}
	
	@PutMapping("/api-keys/{apiKeyId}/revoke")
    public ResponseEntity<Void> revokeApiKey(
            @PathVariable Long apiKeyId) {

        developerService.revokeApiKey(apiKeyId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/applications/{applicationId}/api-keys")
    public ResponseEntity<List<ApiKeyResponse>> getApiKeys(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                developerService.getApiKeys(applicationId));
    }
	
}
