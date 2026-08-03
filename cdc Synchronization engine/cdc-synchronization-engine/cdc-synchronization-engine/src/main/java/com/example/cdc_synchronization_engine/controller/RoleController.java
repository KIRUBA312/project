package com.example.cdc_synchronization_engine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.cdc_synchronization_engine.dto.RoleRequest;
import com.example.cdc_synchronization_engine.dto.RoleResponse;
import com.example.cdc_synchronization_engine.dto.UserRoleRequest;
import com.example.cdc_synchronization_engine.dto.UserRoleResponse;
import com.example.cdc_synchronization_engine.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
@Validated
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(
            @Valid @RequestBody RoleRequest request) {

        RoleResponse response =
                roleService.createRole(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

   
    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {

        List<RoleResponse> response =
                roleService.getAllRoles();

        return ResponseEntity.ok(response);
    }


    @PostMapping("/assign")
    public ResponseEntity<UserRoleResponse> assignRole(
            @Valid @RequestBody UserRoleRequest request) {

        UserRoleResponse response =
                roleService.assignRole(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}