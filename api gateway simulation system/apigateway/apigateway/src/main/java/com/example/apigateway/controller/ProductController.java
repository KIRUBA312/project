package com.example.apigateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apigateway.dto.ProductRequestDto;
import com.example.apigateway.dto.ProductResponseDto;
import com.example.apigateway.service.ProductService;
import com.example.apigateway.util.ApiKeyValidator;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductResponseDto> createProduct(
			@RequestBody ProductRequestDto productRequestDto,
			@RequestHeader(value = "X-API-KEY",required = false)String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(productService.createProduct(productRequestDto));
		
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> getAllProducts(
			@RequestHeader(value = "X-API-KEY",required = false)String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProductById(
			@PathVariable Long id,
			@RequestHeader(value = "X-API-KEY",required = false)String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		return ResponseEntity.ok(productService.getProductById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDto> updateProduct(
			@PathVariable Long id,
			@RequestBody ProductRequestDto productRequestDto,
			@RequestHeader(value = "X-API-KEY", required = false)String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(productService.updateProduct(id, productRequestDto));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(
			@PathVariable Long id,
			@RequestHeader(value = "X-API-KEY",required = false)
			String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		productService.deleteProduct(id);
		
		return ResponseEntity.ok("Product with id "+id+" deleted successfully");
		
	}
	
}
