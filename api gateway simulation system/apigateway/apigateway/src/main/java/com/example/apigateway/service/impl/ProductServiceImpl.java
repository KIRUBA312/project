package com.example.apigateway.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apigateway.dto.ProductRequestDto;
import com.example.apigateway.dto.ProductResponseDto;
import com.example.apigateway.entity.Products;
import com.example.apigateway.entity.RequestLog;
import com.example.apigateway.repository.ProductRepository;
import com.example.apigateway.service.LogService;
import com.example.apigateway.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private LogService logService;

	@Override
	public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
		// TODO Auto-generated method stub
		Products products = new Products();
		products.setProductName(productRequestDto.getProductName());
		products.setPrice(productRequestDto.getPrice());
		products.setQuantity(productRequestDto.getQuantity());
		
		Products savProducts = productRepository.save(products);
		
		saveLog("api/products","POST");
		
		return maptoDto(savProducts);
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		// TODO Auto-generated method stub
		
		List<Products> products = productRepository.findAll();
		List<ProductResponseDto> responseList = new ArrayList<>();
		for(Products product: products) {
			responseList.add(maptoDto(product));
		}
		saveLog("/api/products","GET");
		
		return responseList;
	}

	@Override
	public ProductResponseDto getProductById(Long id) {
		// TODO Auto-generated method stub
		Products product = productRepository.findById(id)
				.orElseThrow(() ->new RuntimeException("Product Not Found"));
		saveLog("/api/products/"+id,"GET");
		return maptoDto(product);
	}

	@Override
	public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
		// TODO Auto-generated method stub
		Products product = productRepository.findById(id)
				.orElseThrow(() ->new RuntimeException("Product Not Found"));
		product.setProductName(productRequestDto.getProductName());
		product.setPrice(productRequestDto.getPrice());
		product.setQuantity(productRequestDto.getQuantity());
		Products updatedProduct = productRepository.save(product);
		saveLog("/api/products/"+id,"PUT");
		return maptoDto(updatedProduct);
	}


	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
		saveLog("/api/products/"+id,"DELETE");
		
	}
	private ProductResponseDto maptoDto(Products Product) {
		// TODO Auto-generated method stub
		ProductResponseDto dto = new ProductResponseDto();
		dto.setId(Product.getId());
		dto.setProduct(Product.getProductName());
		dto.setPrice(Product.getPrice());
		dto.setQuantity(Product.getQuantity());
		return dto;
	}
	private void saveLog(String path, String method) {
		// TODO Auto-generated method stub
		RequestLog log = new RequestLog();
		log.setRequestPath(path);
		log.setMethod(method);
		log.setTimestamp(LocalDateTime.now());
		log.setStatus("SUCCESS");
		
		logService.saveLog(log);
	}
}
