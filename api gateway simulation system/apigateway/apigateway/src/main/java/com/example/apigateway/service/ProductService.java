package com.example.apigateway.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.apigateway.dto.ProductRequestDto;
import com.example.apigateway.dto.ProductResponseDto;

public interface ProductService {

	ProductResponseDto createProduct(ProductRequestDto productRequestDto);

	List<ProductResponseDto> getAllProducts();

	ProductResponseDto getProductById(Long id);

	ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

	void deleteProduct(Long id);

}
