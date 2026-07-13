package com.example.enterprise_iam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import com.example.enterprise_iam.dto.request.RoleRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.RoleResponseDto;
import com.example.enterprise_iam.entity.Role;
import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.entity.UserRole;
import com.example.enterprise_iam.exception.ResourceAlreadyExistsException;
import com.example.enterprise_iam.exception.ResourceNotFoundException;
import com.example.enterprise_iam.repository.RoleRepository;
import com.example.enterprise_iam.repository.UserRepository;
import com.example.enterprise_iam.repository.UserRoleRepository;
import com.example.enterprise_iam.service.impl.RoleServiceImpl;
import com.example.enterprise_iam.util.MapperUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private MapperUtil mapperUtil;

    @InjectMocks
    private RoleServiceImpl roleService;

    private Role role;
    private RoleRequestDto request;
    private RoleResponseDto response;

    @BeforeEach
    void setUp() {

        role = new Role();
        role.setId(1L);
        role.setName("ROLE_ADMIN");
        role.setDescription("Administrator");

        request = new RoleRequestDto();
        request.setName("ROLE_ADMIN");
        request.setDescription("Administrator");

        response = new RoleResponseDto();
        response.setId(1L);
        response.setName("ROLE_ADMIN");
        response.setDescription("Administrator");
    }

    @Test
    void createRole_Success() {

        when(roleRepository.findByName(request.getName()))
                .thenReturn(Optional.empty());

        when(roleRepository.save(any(Role.class)))
                .thenReturn(role);

        when(mapperUtil.toRoleResponse(role))
                .thenReturn(response);

        RoleResponseDto result = roleService.createRole(request);

        assertNotNull(result);
        assertEquals("ROLE_ADMIN", result.getName());

        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void createRole_AlreadyExists() {

        when(roleRepository.findByName(request.getName()))
                .thenReturn(Optional.of(role));

        assertThrows(ResourceAlreadyExistsException.class,
                () -> roleService.createRole(request));
    }

    @Test
    void getRoleById_Success() {

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        when(mapperUtil.toRoleResponse(role))
                .thenReturn(response);

        RoleResponseDto result = roleService.getRoleById(1L);

        assertEquals("ROLE_ADMIN", result.getName());
    }

    @Test
    void getRoleById_NotFound() {

        when(roleRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> roleService.getRoleById(1L));
    }

    @Test
    void updateRole_Success() {

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        when(roleRepository.save(any(Role.class)))
                .thenReturn(role);

        when(mapperUtil.toRoleResponse(role))
                .thenReturn(response);

        RoleResponseDto result =
                roleService.updateRole(1L, request);

        assertEquals("ROLE_ADMIN", result.getName());

        verify(roleRepository).save(role);
    }

    @Test
    void deleteRole_Success() {

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        ApiResponseDto result =
                roleService.deleteRole(1L);

        assertTrue(result.getSuccess());

        verify(roleRepository).delete(role);
    }

    @Test
    void getAllRoles_Success() {

        when(roleRepository.findAll())
                .thenReturn(Arrays.asList(role));

        when(mapperUtil.toRoleResponse(role))
                .thenReturn(response);

        assertEquals(1,
                roleService.getAllRoles().size());
    }

    @Test
    void getAllRoles_Empty() {

        when(roleRepository.findAll())
                .thenReturn(Collections.emptyList());

        assertTrue(roleService.getAllRoles().isEmpty());
    }

    @Test
    void assignRoleToUser_Success() {

        User user = new User();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        when(userRoleRepository.findByUserAndRole(user, role))
                .thenReturn(Optional.empty());

        ApiResponseDto result =
                roleService.assignRoleToUser(1L, 1L);

        assertTrue(result.getSuccess());

        verify(userRoleRepository).save(any(UserRole.class));
    }

    @Test
    void assignRoleToUser_AlreadyAssigned() {

        User user = new User();
        UserRole userRole = new UserRole();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        when(userRoleRepository.findByUserAndRole(user, role))
                .thenReturn(Optional.of(userRole));

        assertThrows(ResourceAlreadyExistsException.class,
                () -> roleService.assignRoleToUser(1L,1L));
    }

    @Test
    void removeRoleFromUser_Success() {

        User user = new User();
        UserRole userRole = new UserRole();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        when(userRoleRepository.findByUserAndRole(user, role))
                .thenReturn(Optional.of(userRole));

        ApiResponseDto result =
                roleService.removeRoleFromUser(1L,1L);

        assertTrue(result.getSuccess());

        verify(userRoleRepository).delete(userRole);
    }

    @Test
    void removeRoleFromUser_NotAssigned() {

        User user = new User();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));

        when(userRoleRepository.findByUserAndRole(user, role))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> roleService.removeRoleFromUser(1L,1L));
    }

}