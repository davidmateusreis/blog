package com.david.backend.controller;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.david.backend.dto.JwtAuthResponse;
import com.david.backend.dto.JwtAuthRequest;
import com.david.backend.service.AuthService;

import jakarta.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/login")
public class AuthController {

    @Autowired
    private AuthService authService;

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody JwtAuthRequest jwtAuthRequest) {
        String token = authService.login(jwtAuthRequest);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
