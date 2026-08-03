package com.example.cdc_synchronization_engine.service;

import java.util.List;

import com.example.cdc_synchronization_engine.dto.ProductRequest;
import com.example.cdc_synchronization_engine.dto.ProductResponse;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);

    ProductResponse getProduct(Long id);

    List<ProductResponse> getAllProducts();
}