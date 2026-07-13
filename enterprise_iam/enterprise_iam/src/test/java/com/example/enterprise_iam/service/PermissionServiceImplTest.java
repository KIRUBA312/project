package com.example.enterprise_iam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import com.example.enterprise_iam.dto.request.PermissionRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.PermissionResponseDto;
import com.example.enterprise_iam.entity.Permission;
import com.example.enterprise_iam.entity.Role;
import com.example.enterprise_iam.entity.RolePermission;
import com.example.enterprise_iam.exception.ResourceAlreadyExistsException;
import com.example.enterprise_iam.exception.ResourceNotFoundException;
import com.example.enterprise_iam.repository.PermissionRepository;
import com.example.enterprise_iam.repository.RolePermissionRepository;
import com.example.enterprise_iam.repository.RoleRepository;
import com.example.enterprise_iam.service.impl.PermissionServiceImpl;
import com.example.enterprise_iam.util.MapperUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PermissionServiceImplTest {

    @Mock
    private PermissionRepository permissionRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RolePermissionRepository rolePermissionRepository;

    @Mock
    private MapperUtil mapperUtil;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    private Permission permission;
    private PermissionRequestDto request;
    private PermissionResponseDto response;

    @BeforeEach
    void setUp() {

        permission = new Permission();
        permission.setId(1L);
        permission.setName("CREATE_USER");
        permission.setDescription("Create User");

        request = new PermissionRequestDto();
        request.setName("CREATE_USER");
        request.setDescription("Create User");

        response = new PermissionResponseDto();
        response.setId(1L);
        response.setName("CREATE_USER");
        response.setDescription("Create User");
    }

    @Test
    void createPermission_Success() {

        when(permissionRepository.findByName(request.getName()))
                .thenReturn(Optional.empty());

        when(permissionRepository.save(any(Permission.class)))
                .thenReturn(permission);

        when(mapperUtil.toPermissionResponse(permission))
                .thenReturn(response);

        PermissionResponseDto result =
                permissionService.createPermission(request);

        assertNotNull(result);
        assertEquals("CREATE_USER", result.getName());

        verify(permissionRepository).save(any(Permission.class));
    }

    @Test
    void createPermission_AlreadyExists() {

        when(permissionRepository.findByName(request.getName()))
                .thenReturn(Optional.of(permission));

        assertThrows(ResourceAlreadyExistsException.class,
                () -> permissionService.createPermission(request));
    }

    @Test
    void getPermissionById_Success() {

        when(permissionRepository.findById(1L))
                .thenReturn(Optional.of(permission));

        when(mapperUtil.toPermissionResponse(permission))
                .thenReturn(response);

        PermissionResponseDto result =
                permissionService.getPermissionById(1L);

        assertEquals("CREATE_USER", result.getName());
    }

    @Test
    void getPermissionById_NotFound() {

        when(permissionRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> permissionService.getPermissionById(1L));
    }

    @Test
    void updatePermission_Success() {

        when(permissionRepository.findById(1L))
                .thenReturn(Optional.of(permission));

        when(permissionRepository.save(any(Permission.class)))
                .thenReturn(permission);

        when(mapperUtil.toPermissionResponse(permission))
                .thenReturn(response);

        PermissionResponseDto result =
                permissionService.updatePermission(1L, request);

        assertEquals("CREATE_USER", result.getName());

        verify(permissionRepository).save(permission);
    }

    @Test
    void deletePermission_Success() {

        when(permissionRepository.findById(1L))
                .thenReturn(Optional.of(permission));

        ApiResponseDto result =
                permissionService.deletePermission(1L);

        assertTrue(result.getSuccess());

        verify(permissionRepository).delete(permission);
    }

    @Test
    void getAllPermission_Success() {

        when(permissionRepository.findAll())
                .thenReturn(Arrays.asList(permission));

        when(mapperUtil.toPermissionResponse(permission))
                .thenReturn(response);

        assertEquals(1,
                permissionService.getAllPermission().size());
    }

    @Test
    void getAllPermission_Empty() {

        when(permissionRepository.findAll())
                .thenReturn(Collections.emptyList());

        assertTrue(permissionService.getAllPermission().isEmpty());
    }

    @Test
    void assignPermissionToRole_Success() {

        Role role = new Role();

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        when(permissionRepository.findById(1L))
                .thenReturn(Optional.of(permission));

        when(rolePermissionRepository.findByRoleAndPermission(role, permission))
                .thenReturn(Optional.empty());

        ApiResponseDto result =
                permissionService.assignPermissionToRole(1L, 1L);

        assertTrue(result.getSuccess());

        verify(rolePermissionRepository).save(any(RolePermission.class));
    }

    @Test
    void assignPermissionToRole_AlreadyAssigned() {

        Role role = new Role();
        RolePermission rolePermission = new RolePermission();

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        when(permissionRepository.findById(1L))
                .thenReturn(Optional.of(permission));

        when(rolePermissionRepository.findByRoleAndPermission(role, permission))
                .thenReturn(Optional.of(rolePermission));

        assertThrows(ResourceAlreadyExistsException.class,
                () -> permissionService.assignPermissionToRole(1L,1L));
    }

    @Test
    void removePermissionFromRole_Success() {

        Role role = new Role();
        RolePermission rolePermission = new RolePermission();

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        when(permissionRepository.findById(1L))
                .thenReturn(Optional.of(permission));

        when(rolePermissionRepository.findByRoleAndPermission(role, permission))
                .thenReturn(Optional.of(rolePermission));

        ApiResponseDto result =
                permissionService.removePermissionFromRole(1L,1L);

        assertTrue(result.getSuccess());

        verify(rolePermissionRepository).delete(rolePermission);
    }

    @Test
    void removePermissionFromRole_NotAssigned() {

        Role role = new Role();

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        when(permissionRepository.findById(1L))
                .thenReturn(Optional.of(permission));

        when(rolePermissionRepository.findByRoleAndPermission(role, permission))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> permissionService.removePermissionFromRole(1L,1L));
    }
}