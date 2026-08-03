package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.ProductRequest;
import com.example.cdc_synchronization_engine.dto.ProductResponse;
import com.example.cdc_synchronization_engine.entity.Product;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request){

        Product product=new Product();

        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setActive(request.getActive());

        return product;
    }

    public ProductResponse toResponse(Product product){

        ProductResponse response=new ProductResponse();

        response.setId(product.getId());
        response.setProductCode(product.getProductCode());
        response.setProductName(product.getProductName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setCategory(product.getCategory());
        response.setActive(product.getActive());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());

        return response;
    }
    public void updateEntity(
            Product product,
            ProductRequest request) {

        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setActive(request.getActive());
    }
}