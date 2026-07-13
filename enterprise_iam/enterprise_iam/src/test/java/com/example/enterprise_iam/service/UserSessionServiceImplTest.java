package com.example.enterprise_iam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.entity.UserSession;
import com.example.enterprise_iam.repository.UserSessionRepository;
import com.example.enterprise_iam.service.impl.UserSessionServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserSessionServiceImplTest {

    @Mock
    private UserSessionRepository userSessionRepository;

    @InjectMocks
    private UserSessionServiceImpl userSessionService;

    private User user;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setId(1L);
        user.setEmail("admin@test.com");
    }

    @Test
    void createSession_Success() {

        userSessionService.createSession(
                user,
                "jwt-token",
                "Chrome",
                "127.0.0.1");

        ArgumentCaptor<UserSession> captor =
                ArgumentCaptor.forClass(UserSession.class);

        verify(userSessionRepository).save(captor.capture());

        UserSession session = captor.getValue();

        assertEquals(user, session.getUser());
        assertEquals("jwt-token", session.getJwtToken());
        assertEquals("Chrome", session.getDevice());
        assertEquals("127.0.0.1", session.getIpAddress());
        assertTrue(session.getActive());
        assertNotNull(session.getLoginTime());
    }

    @Test
    void logoutSession_Success() {

        UserSession session = new UserSession();

        session.setJwtToken("jwt-token");
        session.setActive(true);

        when(userSessionRepository.findByJwtToken("jwt-token"))
                .thenReturn(Optional.of(session));

        userSessionService.logoutSession("jwt-token");

        verify(userSessionRepository).save(session);

        assertFalse(session.getActive());
        assertNotNull(session.getLogoutTime());
    }

    @Test
    void logoutSession_SessionNotFound() {

        when(userSessionRepository.findByJwtToken("jwt-token"))
                .thenReturn(Optional.empty());

        assertDoesNotThrow(() ->
                userSessionService.logoutSession("jwt-token"));

        verify(userSessionRepository, never())
                .save(any(UserSession.class));
    }
}