package com.example.enterprise_iam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.enterprise_iam.dto.request.ForgotPasswordRequestDto;
import com.example.enterprise_iam.dto.request.LoginRequestDto;
import com.example.enterprise_iam.dto.request.RefreshTokenRequestDto;
import com.example.enterprise_iam.dto.request.ResetPasswordRequestDto;
import com.example.enterprise_iam.dto.request.UserRegistrationRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.JwtResponseDto;
import com.example.enterprise_iam.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AuthControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(authController)
                .build();
    }

    @Test
    void register() throws Exception {

        UserRegistrationRequestDto request = new UserRegistrationRequestDto();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john@gmail.com");
        request.setPassword("Password@123");
        request.setPhone("9876543210");

        when(authService.register(any(UserRegistrationRequestDto.class)))
                .thenReturn(new ApiResponseDto(true, "Registered Successfully"));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void login() throws Exception {

        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("john@gmail.com");
        request.setPassword("Password@123");

        JwtResponseDto response = new JwtResponseDto();
        response.setAccessToken("access");
        response.setRefreshToken("refresh");
        response.setExpiresIn(3600L);

        when(authService.login(any(LoginRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void refreshToken() throws Exception {

        RefreshTokenRequestDto request = new RefreshTokenRequestDto();
        request.setRefreshToken("refresh");

        JwtResponseDto response = new JwtResponseDto();
        response.setAccessToken("newAccess");
        response.setRefreshToken("newRefresh");
        response.setExpiresIn(3600L);

        when(authService.refreshToken(any(RefreshTokenRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/refresh-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void logout() throws Exception {

        when(authService.logout(anyString()))
                .thenReturn(new ApiResponseDto(true, "Logged out"));

        mockMvc.perform(post("/api/auth/logout")
                        .header("Authorization", "Bearer token"))
                .andExpect(status().isOk());
    }

    @Test
    void verifyEmail() throws Exception {

        when(authService.verifyEmail(anyString()))
                .thenReturn(new ApiResponseDto(true, "Verified"));

        mockMvc.perform(get("/api/auth/verify")
                        .param("token", "abc"))
                .andExpect(status().isOk());
    }

    @Test
    void forgotPassword() throws Exception {

        ForgotPasswordRequestDto request = new ForgotPasswordRequestDto();
        request.setEmail("john@gmail.com");

        when(authService.forgotPassword(any(ForgotPasswordRequestDto.class)))
                .thenReturn(new ApiResponseDto(true, "Mail Sent"));

        mockMvc.perform(post("/api/auth/forgot-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void resetPassword() throws Exception {

        ResetPasswordRequestDto request = new ResetPasswordRequestDto();
        request.setToken("token123");
        request.setNewPassword("Password@123");

        when(authService.resetPassword(any(ResetPasswordRequestDto.class)))
                .thenReturn(new ApiResponseDto(true, "Password Reset"));

        mockMvc.perform(post("/api/auth/reset-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}