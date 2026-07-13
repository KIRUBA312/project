package com.example.enterprise_iam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.enterprise_iam.dto.request.RoleRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.RoleResponseDto;
import com.example.enterprise_iam.security.CustomUserDetailsService;
import com.example.enterprise_iam.security.JwtAuthenticationEntryPoint;
import com.example.enterprise_iam.security.JwtAuthenticationFilter;
import com.example.enterprise_iam.service.RoleService;
import com.example.enterprise_iam.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RoleController.class)
@AutoConfigureMockMvc(addFilters = false)
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RoleService roleService;

    // -------- Mock Security Beans --------

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private AuthenticationProvider authenticationProvider;

    @MockitoBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    // ------------------------------------

    @Test
    void createRole() throws Exception {

        RoleRequestDto request = new RoleRequestDto();
        request.setName("ROLE_ADMIN");
        request.setDescription("Administrator");

        RoleResponseDto response = new RoleResponseDto();
        response.setId(1L);
        response.setName("ROLE_ADMIN");
        response.setDescription("Administrator");

        when(roleService.createRole(any(RoleRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/roles")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("ROLE_ADMIN"));
    }

    @Test
    void updateRole() throws Exception {

        RoleRequestDto request = new RoleRequestDto();
        request.setName("ROLE_MANAGER");
        request.setDescription("Manager");

        RoleResponseDto response = new RoleResponseDto();
        response.setId(1L);
        response.setName("ROLE_MANAGER");
        response.setDescription("Manager");

        when(roleService.updateRole(eq(1L), any(RoleRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/roles/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ROLE_MANAGER"));
    }

    @Test
    void deleteRole() throws Exception {

        ApiResponseDto response =
                new ApiResponseDto(true, "Role deleted successfully");

        when(roleService.deleteRole(1L))
                .thenReturn(response);

        mockMvc.perform(delete("/api/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void getRole() throws Exception {

        RoleResponseDto response = new RoleResponseDto();
        response.setId(1L);
        response.setName("ROLE_ADMIN");
        response.setDescription("Administrator");

        when(roleService.getRoleById(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("ROLE_ADMIN"));
    }

    @Test
    void getAllRoles() throws Exception {

        RoleResponseDto role1 = new RoleResponseDto();
        role1.setId(1L);
        role1.setName("ROLE_ADMIN");

        RoleResponseDto role2 = new RoleResponseDto();
        role2.setId(2L);
        role2.setName("ROLE_USER");

        when(roleService.getAllRoles())
                .thenReturn(List.of(role1, role2));

        mockMvc.perform(get("/api/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("ROLE_ADMIN"))
                .andExpect(jsonPath("$[1].name").value("ROLE_USER"));
    }

    @Test
    void assignRole() throws Exception {

        ApiResponseDto response =
                new ApiResponseDto(true, "Role assigned successfully");

        when(roleService.assignRoleToUser(2L, 1L))
                .thenReturn(response);

        mockMvc.perform(post("/api/roles/1/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void removeRole() throws Exception {

        ApiResponseDto response =
                new ApiResponseDto(true, "Role removed successfully");

        when(roleService.removeRoleFromUser(2L, 1L))
                .thenReturn(response);

        mockMvc.perform(delete("/api/roles/1/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}