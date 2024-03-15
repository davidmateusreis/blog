package com.david.backend.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthRequest {
    @Size(min = 4, message = "Username must be at least 4 characters")
    private String username;
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
