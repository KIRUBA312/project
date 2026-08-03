package com.example.cdc_synchronization_engine.service;

import java.util.List;

import com.example.cdc_synchronization_engine.dto.CustomerRequest;
import com.example.cdc_synchronization_engine.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse updateCustomer(
            Long id,
            CustomerRequest request);

    void deleteCustomer(Long id);

    CustomerResponse getCustomer(Long id);

    List<CustomerResponse> getAllCustomers();
}