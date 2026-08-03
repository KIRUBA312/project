package com.example.cdc_synchronization_engine.service;

import java.util.List;

import com.example.cdc_synchronization_engine.dto.RoleRequest;
import com.example.cdc_synchronization_engine.dto.RoleResponse;
import com.example.cdc_synchronization_engine.dto.UserRoleRequest;
import com.example.cdc_synchronization_engine.dto.UserRoleResponse;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);

    List<RoleResponse> getAllRoles();

    UserRoleResponse assignRole(UserRoleRequest request);
}