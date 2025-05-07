package com.filmotokio.DTO.REST;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String email;
    private String token;

    public AuthResponse(String token) {
    }
}
