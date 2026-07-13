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

import com.example.enterprise_iam.dto.request.PermissionRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.PermissionResponseDto;
import com.example.enterprise_iam.security.CustomUserDetailsService;
import com.example.enterprise_iam.security.JwtAuthenticationEntryPoint;
import com.example.enterprise_iam.security.JwtAuthenticationFilter;
import com.example.enterprise_iam.service.PermissionService;
import com.example.enterprise_iam.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PermissionController.class)
@AutoConfigureMockMvc(addFilters = false)
class PermissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PermissionService permissionService;

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

    @Test
    void createPermission() throws Exception {

        PermissionRequestDto request = new PermissionRequestDto();
        request.setName("READ_USER");
        request.setDescription("Read User");

        PermissionResponseDto response = new PermissionResponseDto();
        response.setId(1L);
        response.setName("READ_USER");
        response.setDescription("Read User");

        when(permissionService.createPermission(any(PermissionRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/permissions")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("READ_USER"));
    }

    @Test
    void updatePermission() throws Exception {

        PermissionRequestDto request = new PermissionRequestDto();
        request.setName("WRITE_USER");
        request.setDescription("Write User");

        PermissionResponseDto response = new PermissionResponseDto();
        response.setId(1L);
        response.setName("WRITE_USER");

        when(permissionService.updatePermission(eq(1L), any(PermissionRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/permissions/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("WRITE_USER"));
    }

    @Test
    void deletePermission() throws Exception {

        ApiResponseDto response =
                new ApiResponseDto(true, "Permission deleted successfully");

        when(permissionService.deletePermission(1L))
                .thenReturn(response);

        mockMvc.perform(delete("/api/permissions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void getPermission() throws Exception {

        PermissionResponseDto response = new PermissionResponseDto();
        response.setId(1L);
        response.setName("READ_USER");

        when(permissionService.getPermissionById(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/permissions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("READ_USER"));
    }

    @Test
    void getAllPermissions() throws Exception {

        PermissionResponseDto p1 = new PermissionResponseDto();
        p1.setId(1L);
        p1.setName("READ_USER");

        PermissionResponseDto p2 = new PermissionResponseDto();
        p2.setId(2L);
        p2.setName("WRITE_USER");

        when(permissionService.getAllPermission())
                .thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/permissions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("READ_USER"))
                .andExpect(jsonPath("$[1].name").value("WRITE_USER"));
    }

    @Test
    void assignPermissionToRole() throws Exception {

        ApiResponseDto response =
                new ApiResponseDto(true, "Permission assigned successfully");

        when(permissionService.assignPermissionToRole(1L, 2L))
                .thenReturn(response);

        mockMvc.perform(post("/api/permissions/2/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
    
    @Test
    void removePermissionFromRole() throws Exception {

        ApiResponseDto response =
                new ApiResponseDto(true, "Permission removed successfully");

        when(permissionService.removePermissionFromRole(1L, 2L))
                .thenReturn(response);

        mockMvc.perform(delete("/api/permissions/2/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
   
}