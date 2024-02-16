package com.david.backend.dto;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Set<String> roles;
    private boolean active;

    public JwtAuthResponse(String accessToken, Set<String> roles, boolean active) {
        this.accessToken = accessToken;
        this.roles = roles;
        this.active = active;
    }
}
