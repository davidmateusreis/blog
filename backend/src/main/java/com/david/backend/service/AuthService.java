package com.david.backend.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.david.backend.dto.JwtAuthRequest;
import com.david.backend.exception.InvalidPasswordException;
import com.david.backend.security.JwtTokenProvider;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private CustomUserDetailsService customUserDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String login(JwtAuthRequest jwtAuthRequest) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

        if (!bCryptPasswordEncoder.matches(jwtAuthRequest.getPassword(), userDetails.getPassword())) {
            throw new InvalidPasswordException("Your password may be wrong!");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtAuthRequest.getUsername(),
                jwtAuthRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
}
