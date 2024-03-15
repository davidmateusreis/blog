package com.david.backend.service;

import com.david.backend.dto.JwtAuthRequest;
import com.david.backend.dto.JwtAuthResponse;
import com.david.backend.entity.User;
import com.david.backend.exception.InvalidPasswordException;
import com.david.backend.exception.UsernameNotExistsException;
import com.david.backend.repository.UserRepository;
import com.david.backend.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Optional;

public class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() {
        // Arrange
        JwtAuthRequest authRequest = new JwtAuthRequest("testUser", "password");
        UserDetails userDetails = mock(UserDetails.class);
        Authentication authentication = mock(Authentication.class);

        when(customUserDetailsService.loadUserByUsername("testUser")).thenReturn(userDetails);
        when(bCryptPasswordEncoder.matches("password", userDetails.getPassword())).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn("generatedToken");
        when(userRepository.findByUsername("testUser"))
                .thenReturn(Optional.of(new User(1L, "testUser", "Test User", "test@example.com", "encodedPassword",
                        Collections.emptySet(), true)));

        // Act
        JwtAuthResponse jwtAuthResponse = authService.login(authRequest);

        // Assert
        assertNotNull(jwtAuthResponse);
        assertEquals("generatedToken", jwtAuthResponse.getAccessToken());
        assertEquals("Bearer", jwtAuthResponse.getTokenType());
        // Add assertions for roles and isActive based on your expectations

        // Verify that the dependencies were called
        verify(customUserDetailsService, times(1)).loadUserByUsername("testUser");
        verify(bCryptPasswordEncoder, times(1)).matches("password", userDetails.getPassword());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider, times(1)).generateToken(authentication);
        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    public void testLogin_InvalidPassword() {
        // Arrange
        JwtAuthRequest authRequest = new JwtAuthRequest("testUser", "wrongPassword");
        UserDetails userDetails = mock(UserDetails.class);

        when(customUserDetailsService.loadUserByUsername("testUser")).thenReturn(userDetails);
        when(bCryptPasswordEncoder.matches("wrongPassword", userDetails.getPassword())).thenReturn(false);

        // Act and Assert
        assertThrows(InvalidPasswordException.class, () -> authService.login(authRequest));

        // Verify that the dependencies were called
        verify(customUserDetailsService, times(1)).loadUserByUsername("testUser");
        verify(bCryptPasswordEncoder, times(1)).matches("wrongPassword", userDetails.getPassword());
        // Ensure that other dependencies were not called
        verifyNoMoreInteractions(authenticationManager, jwtTokenProvider, userRepository);
    }

    @Test
    public void testLogin_UserNotFound() {
        // Arrange
        JwtAuthRequest authRequest = new JwtAuthRequest("nonexistentUser", "password");

        when(customUserDetailsService.loadUserByUsername("nonexistentUser")).thenReturn(null);

        // Act and Assert
        assertThrows(UsernameNotExistsException.class, () -> authService.login(authRequest));

        // Verify that the dependency was called
        verify(customUserDetailsService, times(1)).loadUserByUsername("nonexistentUser");
        // Ensure that other dependencies were not called
        verifyNoMoreInteractions(authenticationManager, bCryptPasswordEncoder, jwtTokenProvider, userRepository);
    }
}
