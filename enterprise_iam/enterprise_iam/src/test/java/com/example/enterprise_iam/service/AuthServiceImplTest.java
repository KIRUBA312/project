package com.example.enterprise_iam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.enterprise_iam.dto.request.UserRegistrationRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.entity.RefreshToken;
import com.example.enterprise_iam.entity.Role;
import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.entity.UserRole;
import com.example.enterprise_iam.entity.VerificationToken;
import com.example.enterprise_iam.exception.ResourceAlreadyExistsException;
import com.example.enterprise_iam.repository.PasswordResetTokenRepository;
import com.example.enterprise_iam.repository.RefreshTokenRepository;
import com.example.enterprise_iam.repository.RoleRepository;
import com.example.enterprise_iam.repository.UserRepository;
import com.example.enterprise_iam.repository.UserRoleRepository;
import com.example.enterprise_iam.repository.VerificationTokenRepository;
import com.example.enterprise_iam.service.impl.AuthServiceImpl;
import com.example.enterprise_iam.util.JwtUtil;
import com.example.enterprise_iam.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private VerificationTokenRepository verificationTokenRepository;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private MapperUtil mapperUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private MailService mailService;

    @Mock
    private UserSessionService userSessionService;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private AuthServiceImpl authService;

    private User user;
    private Role role;
    private UserRegistrationRequestDto registerRequest;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setId(1L);
        user.setEmail("admin@test.com");
        user.setPassword("password");

        role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");

        registerRequest = new UserRegistrationRequestDto();
        registerRequest.setFirstName("Admin");
        registerRequest.setLastName("User");
        registerRequest.setEmail("admin@test.com");
        registerRequest.setPassword("password");
        registerRequest.setPhone("9876543210");
    }

    @Test
    void register_Success() {

        when(userRepository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(false);

        when(mapperUtil.toUser(registerRequest))
                .thenReturn(user);

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        when(roleRepository.findByName("ROLE_USER"))
                .thenReturn(Optional.of(role));

        ApiResponseDto response =
                authService.register(registerRequest);

        assertTrue(response.getSuccess());

        verify(userRepository).save(any(User.class));
        verify(userRoleRepository).save(any(UserRole.class));
        verify(verificationTokenRepository)
                .save(any(VerificationToken.class));
        verify(mailService)
                .sendVerificationEmail(eq(user.getEmail()), anyString());
    }

    @Test
    void register_EmailAlreadyExists() {

        when(userRepository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class,
                () -> authService.register(registerRequest));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void register_DefaultRoleNotFound() {

        when(userRepository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(false);

        when(mapperUtil.toUser(registerRequest))
                .thenReturn(user);

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        when(roleRepository.findByName("ROLE_USER"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> authService.register(registerRequest));
    }

    @Test
    void register_VerificationTokenSaved() {

        when(userRepository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(false);

        when(mapperUtil.toUser(registerRequest))
                .thenReturn(user);

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        when(roleRepository.findByName("ROLE_USER"))
                .thenReturn(Optional.of(role));

        authService.register(registerRequest);

        verify(verificationTokenRepository)
                .save(any(VerificationToken.class));
    }

    @Test
    void register_UserRoleSaved() {

        when(userRepository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(false);

        when(mapperUtil.toUser(registerRequest))
                .thenReturn(user);

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        when(roleRepository.findByName("ROLE_USER"))
                .thenReturn(Optional.of(role));

        authService.register(registerRequest);

        verify(userRoleRepository)
                .save(any(UserRole.class));
    }

    @Test
    void register_VerificationMailSent() {

        when(userRepository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(false);

        when(mapperUtil.toUser(registerRequest))
                .thenReturn(user);

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        when(roleRepository.findByName("ROLE_USER"))
                .thenReturn(Optional.of(role));

        authService.register(registerRequest);

        verify(mailService)
                .sendVerificationEmail(eq(user.getEmail()), anyString());
    }
    
    @Test
    void login_Success() {

        com.example.enterprise_iam.dto.request.LoginRequestDto request =
                new com.example.enterprise_iam.dto.request.LoginRequestDto();

        request.setEmail("admin@test.com");
        request.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        when(authentication.getPrincipal())
                .thenReturn(userDetails);

        when(userDetails.getUsername())
                .thenReturn("admin@test.com");

        user.setEnabled(true);

        when(userRepository.findByEmail("admin@test.com"))
                .thenReturn(Optional.of(user));

        when(jwtUtil.generateAccessToken(userDetails))
                .thenReturn("access-token");

        when(jwtUtil.generateRefreshToken(userDetails))
                .thenReturn("refresh-token");

        var response = authService.login(request);

        assertNotNull(response);
        assertEquals("access-token", response.getAccessToken());
        assertEquals("refresh-token", response.getRefreshToken());

        verify(refreshTokenRepository).save(any(RefreshToken.class));
        verify(userSessionService)
                .createSession(eq(user), eq("access-token"),
                        anyString(), anyString());

        verify(redisService)
                .saveAccessToken(user.getEmail(), "access-token");

        verify(redisService)
                .saveRefreshToken(user.getEmail(), "refresh-token");
    }

    @Test
    void login_UserNotVerified() {

        com.example.enterprise_iam.dto.request.LoginRequestDto request =
                new com.example.enterprise_iam.dto.request.LoginRequestDto();

        request.setEmail("admin@test.com");
        request.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        when(authentication.getPrincipal())
                .thenReturn(userDetails);

        when(userDetails.getUsername())
                .thenReturn("admin@test.com");

        user.setEnabled(false);

        when(userRepository.findByEmail("admin@test.com"))
                .thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class,
                () -> authService.login(request));
    }

    @Test
    void login_UserNotFound() {

        com.example.enterprise_iam.dto.request.LoginRequestDto request =
                new com.example.enterprise_iam.dto.request.LoginRequestDto();

        request.setEmail("admin@test.com");
        request.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        when(authentication.getPrincipal())
                .thenReturn(userDetails);

        when(userDetails.getUsername())
                .thenReturn("admin@test.com");

        when(userRepository.findByEmail("admin@test.com"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> authService.login(request));
    }

    @Test
    void refreshToken_Success() {

        com.example.enterprise_iam.dto.request.RefreshTokenRequestDto request =
                new com.example.enterprise_iam.dto.request.RefreshTokenRequestDto();

        request.setRefreshToken("refresh-token");

        RefreshToken token = new RefreshToken();
        token.setToken("refresh-token");
        token.setUser(user);
        token.setRevoked(false);
        token.setExpiryDate(LocalDateTime.now().plusDays(1));

        when(refreshTokenRepository.findByToken("refresh-token"))
                .thenReturn(Optional.of(token));

        when(jwtUtil.generateAccessToken(any(UserDetails.class)))
                .thenReturn("new-access-token");

        var response = authService.refreshToken(request);

        assertEquals("new-access-token", response.getAccessToken());

        verify(redisService)
                .saveAccessToken(user.getEmail(), "new-access-token");
    }

    @Test
    void refreshToken_NotFound() {

        com.example.enterprise_iam.dto.request.RefreshTokenRequestDto request =
                new com.example.enterprise_iam.dto.request.RefreshTokenRequestDto();

        request.setRefreshToken("invalid");

        when(refreshTokenRepository.findByToken("invalid"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> authService.refreshToken(request));
    }

    @Test
    void refreshToken_Revoked() {

        com.example.enterprise_iam.dto.request.RefreshTokenRequestDto request =
                new com.example.enterprise_iam.dto.request.RefreshTokenRequestDto();

        request.setRefreshToken("refresh-token");

        RefreshToken token = new RefreshToken();
        token.setRevoked(true);

        when(refreshTokenRepository.findByToken("refresh-token"))
                .thenReturn(Optional.of(token));

        assertThrows(RuntimeException.class,
                () -> authService.refreshToken(request));
    }

    @Test
    void refreshToken_Expired() {

        com.example.enterprise_iam.dto.request.RefreshTokenRequestDto request =
                new com.example.enterprise_iam.dto.request.RefreshTokenRequestDto();

        request.setRefreshToken("refresh-token");

        RefreshToken token = new RefreshToken();
        token.setRevoked(false);
        token.setExpiryDate(LocalDateTime.now().minusDays(1));

        when(refreshTokenRepository.findByToken("refresh-token"))
                .thenReturn(Optional.of(token));

        assertThrows(RuntimeException.class,
                () -> authService.refreshToken(request));
    }
    @Test
    void logout_Success() {

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken("refresh-token");
        refreshToken.setUser(user);
        refreshToken.setRevoked(false);

        when(refreshTokenRepository.findByToken("refresh-token"))
                .thenReturn(Optional.of(refreshToken));

        ApiResponseDto response =
                authService.logout("refresh-token");

        assertTrue(response.getSuccess());

        verify(refreshTokenRepository).save(refreshToken);
        verify(userSessionService).logoutSession("refresh-token");
        verify(redisService).clearUserSession(user.getEmail());
    }

    @Test
    void logout_InvalidToken() {

        when(refreshTokenRepository.findByToken("invalid"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> authService.logout("invalid"));
    }

    @Test
    void verifyEmail_Success() {

        VerificationToken token = new VerificationToken();
        token.setToken("verify-token");
        token.setUser(user);
        token.setExpiryDate(LocalDateTime.now().plusHours(1));

        when(verificationTokenRepository.findByToken("verify-token"))
                .thenReturn(Optional.of(token));

        ApiResponseDto response =
                authService.verifyEmail("verify-token");

        assertTrue(response.getSuccess());

        verify(userRepository).save(user);
        verify(verificationTokenRepository).delete(token);
    }

    @Test
    void verifyEmail_InvalidToken() {

        when(verificationTokenRepository.findByToken("verify-token"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> authService.verifyEmail("verify-token"));
    }

    @Test
    void verifyEmail_ExpiredToken() {

        VerificationToken token = new VerificationToken();
        token.setToken("verify-token");
        token.setUser(user);
        token.setExpiryDate(LocalDateTime.now().minusHours(1));

        when(verificationTokenRepository.findByToken("verify-token"))
                .thenReturn(Optional.of(token));

        assertThrows(RuntimeException.class,
                () -> authService.verifyEmail("verify-token"));
    }

    @Test
    void forgotPassword_Success() {

        com.example.enterprise_iam.dto.request.ForgotPasswordRequestDto request =
                new com.example.enterprise_iam.dto.request.ForgotPasswordRequestDto();

        request.setEmail("admin@test.com");

        when(userRepository.findByEmail("admin@test.com"))
                .thenReturn(Optional.of(user));

        ApiResponseDto response =
                authService.forgotPassword(request);

        assertTrue(response.getSuccess());

        verify(passwordResetTokenRepository).save(any());
        verify(mailService)
                .sendPasswordResetEmail(eq(user.getEmail()), anyString());
    }

    @Test
    void forgotPassword_UserNotFound() {

        com.example.enterprise_iam.dto.request.ForgotPasswordRequestDto request =
                new com.example.enterprise_iam.dto.request.ForgotPasswordRequestDto();

        request.setEmail("admin@test.com");

        when(userRepository.findByEmail("admin@test.com"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> authService.forgotPassword(request));
    }

    @Test
    void resetPassword_Success() {

        com.example.enterprise_iam.dto.request.ResetPasswordRequestDto request =
                new com.example.enterprise_iam.dto.request.ResetPasswordRequestDto();

        request.setToken("reset-token");
        request.setNewPassword("newpassword");

        com.example.enterprise_iam.entity.PasswordResetToken token =
                new com.example.enterprise_iam.entity.PasswordResetToken();

        token.setToken("reset-token");
        token.setUser(user);
        token.setUsed(false);
        token.setExpiryDate(LocalDateTime.now().plusHours(1));

        when(passwordResetTokenRepository.findByToken("reset-token"))
                .thenReturn(Optional.of(token));

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encoded-password");

        ApiResponseDto response =
                authService.resetPassword(request);

        assertTrue(response.getSuccess());

        verify(userRepository).save(user);
        verify(passwordResetTokenRepository).save(token);
    }

    @Test
    void resetPassword_InvalidToken() {

        com.example.enterprise_iam.dto.request.ResetPasswordRequestDto request =
                new com.example.enterprise_iam.dto.request.ResetPasswordRequestDto();

        request.setToken("invalid");

        when(passwordResetTokenRepository.findByToken("invalid"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> authService.resetPassword(request));
    }

    @Test
    void resetPassword_AlreadyUsed() {

        com.example.enterprise_iam.dto.request.ResetPasswordRequestDto request =
                new com.example.enterprise_iam.dto.request.ResetPasswordRequestDto();

        request.setToken("reset-token");

        com.example.enterprise_iam.entity.PasswordResetToken token =
                new com.example.enterprise_iam.entity.PasswordResetToken();

        token.setUsed(true);

        when(passwordResetTokenRepository.findByToken("reset-token"))
                .thenReturn(Optional.of(token));

        assertThrows(RuntimeException.class,
                () -> authService.resetPassword(request));
    }

    @Test
    void resetPassword_ExpiredToken() {

        com.example.enterprise_iam.dto.request.ResetPasswordRequestDto request =
                new com.example.enterprise_iam.dto.request.ResetPasswordRequestDto();

        request.setToken("reset-token");

        com.example.enterprise_iam.entity.PasswordResetToken token =
                new com.example.enterprise_iam.entity.PasswordResetToken();

        token.setUsed(false);
        token.setExpiryDate(LocalDateTime.now().minusHours(2));

        when(passwordResetTokenRepository.findByToken("reset-token"))
                .thenReturn(Optional.of(token));

        assertThrows(RuntimeException.class,
                () -> authService.resetPassword(request));
    }

}