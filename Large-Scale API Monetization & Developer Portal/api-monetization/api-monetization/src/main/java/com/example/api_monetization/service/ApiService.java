package com.example.api_monetization.service;

import java.util.List;

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

public interface ApiService {

    ApiCategoryResponse createCategory(ApiCategoryRequest request);

    List<ApiCategoryResponse> getAllCategories();

    ApiResponse createApi(Long publisherId, ApiRequest request);

    ApiResponse updateApi(Long apiId, ApiRequest request);

    ApiResponse getApi(Long apiId);

    List<ApiResponse> getAllApis();

    void deleteApi(Long apiId);

    ApiVersionResponse createVersion(Long apiId,
                                     ApiVersionRequest request);

    List<ApiVersionResponse> getVersions(Long apiId);

    ApiDocumentationResponse createDocumentation(
            Long apiId,
            ApiDocumentationRequest request);

    ApiDocumentationResponse getDocumentation(Long apiId);

    ApiPublishResponse publishApi(Long apiId,
                                  ApiPublishRequest request);

}