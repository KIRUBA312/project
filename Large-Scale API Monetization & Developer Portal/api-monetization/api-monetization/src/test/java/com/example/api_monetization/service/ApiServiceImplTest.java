package com.example.api_monetization.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.api_monetization.dto.api.*;
import com.example.api_monetization.entity.*;
import com.example.api_monetization.enums.PublishRequestStatus;
import com.example.api_monetization.exception.ResourceAlreadyExistsException;
import com.example.api_monetization.exception.ResourceNotFoundException;
import com.example.api_monetization.mapper.*;
import com.example.api_monetization.repository.*;
import com.example.api_monetization.service.impl.ApiServiceImpl;

@ExtendWith(MockitoExtension.class)
class ApiServiceImplTest {

    @InjectMocks
    private ApiServiceImpl service;

    @Mock
    private ApiRepository apiRepository;

    @Mock
    private ApiCategoryRepository apiCategoryRepository;

    @Mock
    private ApiVersionRepository apiVersionRepository;

    @Mock
    private ApiDocumentationRepository apiDocumentationRepository;

    @Mock
    private ApiPublishRequestRepository apiPublishRequestRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApiMapper apiMapper;

    @Mock
    private ApiCategoryMapper apiCategoryMapper;

    @Mock
    private ApiVersionMapper apiVersionMapper;

    @Mock
    private ApiDocumentationMapper apiDocumentationMapper;

    @Mock
    private ApiPublishRequestMapper apiPublishRequestMapper;

    private User publisher;
    private Api api;
    
    @BeforeEach
    void setup(){

        publisher = new User();
        publisher.setId(1L);

        api = new Api();
        api.setId(1L);

    }
    
    @Test
    void createCategorySuccess(){

        ApiCategoryRequest request =
                new ApiCategoryRequest();

        request.setCategoryName("Payments");

        when(apiCategoryRepository.existsByCategoryNameIgnoreCase("Payments"))
                .thenReturn(false);

        ApiCategory entity = new ApiCategory();

        when(apiCategoryMapper.toEntity(request))
                .thenReturn(entity);

        when(apiCategoryRepository.save(entity))
                .thenReturn(entity);

        when(apiCategoryMapper.toResponse(entity))
                .thenReturn(new ApiCategoryResponse());

        ApiCategoryResponse response =
                service.createCategory(request);

        assertNotNull(response);

        verify(apiCategoryRepository).save(entity);
    }
    
    @Test
    void createCategoryAlreadyExists(){

        ApiCategoryRequest request =
                new ApiCategoryRequest();

        request.setCategoryName("Payments");

        when(apiCategoryRepository.existsByCategoryNameIgnoreCase("Payments"))
                .thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class,
                ()->service.createCategory(request));

    }
    @Test
    void createApiSuccess(){

        ApiRequest request = new ApiRequest();

        request.setPublisherId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(publisher));

        when(apiMapper.toEntity(request))
                .thenReturn(api);

        when(apiRepository.save(any(Api.class)))
                .thenReturn(api);

        when(apiMapper.toResponse(api))
                .thenReturn(new ApiResponse());

        ApiResponse response =
                service.createApi(1L,request);

        assertNotNull(response);

    }
    
    @Test
    void createApiPublisherNotFound(){

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                ()->service.createApi(1L,new ApiRequest()));

    }
    @Test
    void getApiSuccess(){

        when(apiRepository.findById(1L))
                .thenReturn(Optional.of(api));

        when(apiMapper.toResponse(api))
                .thenReturn(new ApiResponse());

        ApiResponse response =
                service.getApi(1L);

        assertNotNull(response);

    }
    @Test
    void deleteApiSuccess(){

        when(apiRepository.findById(1L))
                .thenReturn(Optional.of(api));

        service.deleteApi(1L);

        verify(apiRepository).delete(api);

    }
    @Test
    void createVersionSuccess(){

        ApiVersionRequest request =
                new ApiVersionRequest();

        when(apiRepository.findById(1L))
                .thenReturn(Optional.of(api));

        ApiVersion version =
                new ApiVersion();

        when(apiVersionMapper.toEntity(request))
                .thenReturn(version);

        when(apiVersionRepository.save(any()))
                .thenReturn(version);

        when(apiVersionMapper.toResponse(version))
                .thenReturn(new ApiVersionResponse());

        ApiVersionResponse response =
                service.createVersion(1L,request);

        assertNotNull(response);

    }
//    @Test
//    void createDocumentationSuccess(){
//
//        ApiDocumentationRequest request =
//                new ApiDocumentationRequest();
//
//        when(apiRepository.findById(1L))
//                .thenReturn(Optional.of(api));
//
//        when(apiDocumentationRepository.findByApiId(1L))
//                .thenReturn(Optional.empty());
//
//        ApiDocumentation doc =
//                new ApiDocumentation();
//
//        when(apiDocumentationMapper.toEntity(request))
//                .thenReturn(doc);
//
//        when(apiDocumentationRepository.save(any()))
//                .thenReturn(doc);
//
//        when(apiDocumentationMapper.toResponse(doc))
//                .thenReturn(new ApiDocumentationResponse());
//
//        ApiDocumentationResponse response =
//                service.createDocumentation(1L,request);
//
//        assertNotNull(response);
//
//    }
    
    
}