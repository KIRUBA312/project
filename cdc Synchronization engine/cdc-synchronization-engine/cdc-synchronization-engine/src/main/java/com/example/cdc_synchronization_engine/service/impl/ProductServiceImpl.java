package com.example.cdc_synchronization_engine.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.dto.ProductRequest;
import com.example.cdc_synchronization_engine.dto.ProductResponse;
import com.example.cdc_synchronization_engine.entity.Product;
import com.example.cdc_synchronization_engine.exception.ErrorCode;
import com.example.cdc_synchronization_engine.exception.ResourceAlreadyExistsException;
import com.example.cdc_synchronization_engine.exception.ResourceNotFoundException;
import com.example.cdc_synchronization_engine.kafka.producer.CDCEventProducer;
import com.example.cdc_synchronization_engine.mapper.ProductMapper;
import com.example.cdc_synchronization_engine.repository.ProductRepository;
import com.example.cdc_synchronization_engine.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CDCEventProducer cdcEventProducer;

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public ProductResponse createProduct(ProductRequest request) {

        if (request.getProductCode() != null
                && productRepository.existsByProductCode(request.getProductCode())) {

            throw new ResourceAlreadyExistsException(
                    ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Product with code '"
                            + request.getProductCode()
                            + "' already exists");
        }

        Product product = productMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);

        ProductResponse response =
                productMapper.toResponse(savedProduct);

        cdcEventProducer.publishEvent(
                "products-events",
                "PRODUCT",
                savedProduct.getId(),
                "CREATE",
                response
        );

        return response;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public ProductResponse updateProduct(
            Long id,
            ProductRequest request) {

        Product existingProduct =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "Product with id "
                                                + id
                                                + " not found"));

        if (request.getProductCode() != null
                && productRepository.existsByProductCodeAndIdNot(
                        request.getProductCode(),
                        id)) {

            throw new ResourceAlreadyExistsException(
                    ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Product with code '"
                            + request.getProductCode()
                            + "' already exists");
        }

        productMapper.updateEntity(
                existingProduct,
                request
        );

        Product updatedProduct =
                productRepository.save(existingProduct);

        ProductResponse response =
                productMapper.toResponse(updatedProduct);

        cdcEventProducer.publishEvent(
                "products-events",
                "PRODUCT",
                updatedProduct.getId(),
                "UPDATE",
                response
        );

        return response;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long id) {

        Product existingProduct =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "Product with id "
                                                + id
                                                + " not found"));

        productRepository.delete(existingProduct);

        cdcEventProducer.publishEvent(
                "products-events",
                "PRODUCT",
                id,
                "DELETE",
                null
        );
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProduct(Long id) {

        Product product =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "Product with id "
                                                + id
                                                + " not found"));

        return productMapper.toResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("products")
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }
}