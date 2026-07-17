package com.example.api_monetization.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.api_monetization.dto.auth.LoginRequest;
import com.example.api_monetization.dto.auth.LoginResponse;
import com.example.api_monetization.dto.auth.RegisterRequest;
import com.example.api_monetization.dto.user.UserResponse;
import com.example.api_monetization.entity.Role;
import com.example.api_monetization.entity.User;
import com.example.api_monetization.entity.UserRole;
import com.example.api_monetization.enums.AccountStatus;
import com.example.api_monetization.exception.ResourceAlreadyExistsException;
import com.example.api_monetization.mapper.UserMapper;
import com.example.api_monetization.repository.RoleRepository;
import com.example.api_monetization.repository.UserRepository;
import com.example.api_monetization.repository.UserRoleRepository;
import com.example.api_monetization.security.jwt.JwtTokenProvider;
import com.example.api_monetization.service.impl.AuthServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserMapper userMapper;

    private RegisterRequest registerRequest;

    @BeforeEach
    void setup() {

        registerRequest = new RegisterRequest();

        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("john@test.com");
        registerRequest.setPassword("password");
        registerRequest.setPhone("9876543210");

    }

    @Test
    void shouldRegisterUserSuccessfully() {

        when(userRepository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(false);

        Role role = new Role();
        role.setRoleName("ROLE_DEVELOPER");

        when(roleRepository.findByRoleName("ROLE_DEVELOPER"))
                .thenReturn(Optional.of(role));

        when(passwordEncoder.encode(any()))
                .thenReturn("encodedPassword");

        User savedUser = new User();
        savedUser.setId(1L);

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        when(userRoleRepository.save(any(UserRole.class)))
                .thenReturn(new UserRole());

        UserResponse response = new UserResponse();
        response.setId(1L);

        when(userMapper.toResponse(any(User.class)))
                .thenReturn(response);

        UserResponse result = authService.register(registerRequest);

        assertNotNull(result);

        assertEquals(1L, result.getId());

        verify(userRepository).save(any(User.class));

        verify(userRoleRepository).save(any(UserRole.class));

    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        when(userRepository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class,
                () -> authService.register(registerRequest));

    }

    @Test
    void shouldLoginSuccessfully() {

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setEmail("john@test.com");

        loginRequest.setPassword("password");

        User user = new User();

        user.setEmail("john@test.com");

        when(userRepository.findByEmail("john@test.com"))
                .thenReturn(Optional.of(user));

        when(jwtTokenProvider.generateToken(any()))
                .thenReturn("jwt-token");

        UserResponse userResponse = new UserResponse();

        userResponse.setId(1L);

        when(userMapper.toResponse(any(User.class)))
                .thenReturn(userResponse);

        LoginResponse response = authService.login(loginRequest);

        assertEquals("jwt-token", response.getAccessToken());

        assertEquals("Bearer", response.getTokenType());

        verify(authenticationManager).authenticate(
                any(UsernamePasswordAuthenticationToken.class));

    }

}