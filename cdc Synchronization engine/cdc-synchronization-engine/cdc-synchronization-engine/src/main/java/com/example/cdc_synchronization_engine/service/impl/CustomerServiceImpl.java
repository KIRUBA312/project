package com.example.cdc_synchronization_engine.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.dto.CustomerRequest;
import com.example.cdc_synchronization_engine.dto.CustomerResponse;
import com.example.cdc_synchronization_engine.entity.Customer;
import com.example.cdc_synchronization_engine.exception.ErrorCode;
import com.example.cdc_synchronization_engine.exception.ResourceAlreadyExistsException;
import com.example.cdc_synchronization_engine.exception.ResourceNotFoundException;
import com.example.cdc_synchronization_engine.kafka.producer.CDCEventProducer;
import com.example.cdc_synchronization_engine.mapper.CustomerMapper;
import com.example.cdc_synchronization_engine.repository.CustomerRepository;
import com.example.cdc_synchronization_engine.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final CDCEventProducer cdcEventProducer;

    @Override
    @CacheEvict(value = "customers", allEntries = true)
    public CustomerResponse createCustomer(CustomerRequest request) {

        if (customerRepository.existsByCustomerCode(request.getCustomerCode())) {

            throw new ResourceAlreadyExistsException(
                    ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Customer code already exists: " + request.getCustomerCode());
        }

        if (customerRepository.existsByEmail(request.getEmail())) {

            throw new ResourceAlreadyExistsException(
                    ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Customer email already exists: " + request.getEmail());
        }

        Customer customer = customerMapper.toEntity(request);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerResponse response =
                customerMapper.toResponse(savedCustomer);

        cdcEventProducer.publishEvent(
                "customers-events",
                "CUSTOMER",
                savedCustomer.getId(),
                "CREATE",
                response
        );

        return response;
    }

    @Override
    @CacheEvict(value = "customers", allEntries = true)
    public CustomerResponse updateCustomer(Long id,
                                           CustomerRequest request) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "Customer not found with id: " + id));

        if (!customer.getCustomerCode().equals(request.getCustomerCode())) {

            if (customerRepository.existsByCustomerCode(request.getCustomerCode())) {

                throw new ResourceAlreadyExistsException(
                        ErrorCode.RESOURCE_ALREADY_EXISTS,
                        "Customer code already exists: " + request.getCustomerCode());
            }
        }

        if (!customer.getEmail().equals(request.getEmail())) {

            if (customerRepository.existsByEmail(request.getEmail())) {

                throw new ResourceAlreadyExistsException(
                        ErrorCode.RESOURCE_ALREADY_EXISTS,
                        "Customer email already exists: " + request.getEmail());
            }
        }

        customerMapper.updateEntity(customer, request);

        Customer updatedCustomer =
                customerRepository.save(customer);

        CustomerResponse response =
                customerMapper.toResponse(updatedCustomer);

        cdcEventProducer.publishEvent(
                "customers-events",
                "CUSTOMER",
                updatedCustomer.getId(),
                "UPDATE",
                response
        );

        return response;
    }

    @Override
    @CacheEvict(value = "customers", allEntries = true)
    public void deleteCustomer(Long id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "Customer not found with id: " + id));

        customerRepository.delete(customer);

        cdcEventProducer.publishEvent(
                "customers-events",
                "CUSTOMER",
                id,
                "DELETE",
                null
        );
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "customers", key = "#id")
    public CustomerResponse getCustomer(Long id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "Customer not found with id: " + id));

        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("customers")
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }
}