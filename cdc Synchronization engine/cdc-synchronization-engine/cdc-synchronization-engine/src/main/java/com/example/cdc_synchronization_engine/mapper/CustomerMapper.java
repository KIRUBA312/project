package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.CustomerRequest;
import com.example.cdc_synchronization_engine.dto.CustomerResponse;
import com.example.cdc_synchronization_engine.entity.Customer;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest request) {

        if (request == null) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustomerCode(request.getCustomerCode());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());
        customer.setCustomerStatus(request.getCustomerStatus());

        return customer;
    }

    public CustomerResponse toResponse(Customer customer) {

        if (customer == null) {
            return null;
        }

        CustomerResponse response = new CustomerResponse();

        response.setId(customer.getId());
        response.setCustomerCode(customer.getCustomerCode());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setAddress(customer.getAddress());
        response.setCustomerStatus(customer.getCustomerStatus());
        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());

        return response;
    } 
    
    public void updateEntity(
            Customer customer,
            CustomerRequest request) {

        customer.setCustomerCode(request.getCustomerCode());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());
        customer.setCustomerStatus(request.getCustomerStatus());
    }
}