package com.example.api_monetization.controller;

import java.util.List;

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

import com.example.api_monetization.dto.api.ApiCategoryRequest;
import com.example.api_monetization.dto.api.ApiCategoryResponse;
import com.example.api_monetization.dto.api.ApiDocumentationRequest;
import com.example.api_monetization.dto.api.ApiDocumentationResponse;
import com.example.api_monetization.dto.api.ApiPublishRequest;
import com.example.api_monetization.dto.api.ApiPublishResponse;
import com.example.api_monetization.dto.api.ApiRequest;
import com.example.api_monetization.dto.api.ApiResponse;
import com.example.api_monetization.dto.api.ApiVersionRequest;
import com.example.api_monetization.dto.api.ApiVersionResponse;
import com.example.api_monetization.service.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

	private final ApiService apiService;
	
	@PostMapping("/categories")
	public ResponseEntity<ApiCategoryResponse> createCategory(
			@Valid @RequestBody ApiCategoryRequest request){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(apiService.createCategory(request));
	}
	@GetMapping("/categories")
	public ResponseEntity<List<ApiCategoryResponse>> getCategories(){
		return ResponseEntity.ok(apiService.getAllCategories());
	}
	
	@PostMapping("/publishers/{publisherId}")
	public ResponseEntity<ApiResponse> createApi(
			@PathVariable Long publisherId, @Valid
			@RequestBody ApiRequest request){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(apiService.createApi(publisherId,request));
	}
	@PutMapping("/{apiId}")
	public ResponseEntity<ApiResponse> updateApi(
			@PathVariable Long apiId,@Valid @RequestBody ApiRequest request)
	{
		return ResponseEntity.ok(apiService.updateApi(apiId, request));
	}
	
	 @GetMapping("/{apiId}")
	    public ResponseEntity<ApiResponse> getApi(
	            @PathVariable Long apiId) {

	        return ResponseEntity.ok(apiService.getApi(apiId));
    }

    @GetMapping
    public ResponseEntity<List<ApiResponse>> getAllApis() {

        return ResponseEntity.ok(apiService.getAllApis());
    }

    @DeleteMapping("/{apiId}")
    public ResponseEntity<Void> deleteApi(
            @PathVariable Long apiId) {

        apiService.deleteApi(apiId);

        return ResponseEntity.noContent().build();
    }

    
    @PostMapping("/{apiId}/versions")
    public ResponseEntity<ApiVersionResponse> createVersion(
            @PathVariable Long apiId,
            @Valid @RequestBody ApiVersionRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiService.createVersion(apiId, request));
    }

    @GetMapping("/{apiId}/versions")
    public ResponseEntity<List<ApiVersionResponse>> getVersions(
            @PathVariable Long apiId) {

        return ResponseEntity.ok(apiService.getVersions(apiId));
    }

    @PostMapping("/{apiId}/documentation")
    public ResponseEntity<ApiDocumentationResponse> createDocumentation(
            @PathVariable Long apiId,
            @Valid @RequestBody ApiDocumentationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiService.createDocumentation(apiId, request));
    }

    @GetMapping("/{apiId}/documentation")
    public ResponseEntity<ApiDocumentationResponse> getDocumentation(
            @PathVariable Long apiId) {

        return ResponseEntity.ok(apiService.getDocumentation(apiId));
    }

    @PostMapping("/{apiId}/publish")
    public ResponseEntity<ApiPublishResponse> publishApi(
            @PathVariable Long apiId,
            @Valid @RequestBody ApiPublishRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiService.publishApi(apiId, request));
    }
	
	
}
