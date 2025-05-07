package com.filmotokio.service;

import com.filmotokio.DTO.REST.LoginRequest;
import com.filmotokio.DTO.REST.RegisterRequest;
import com.filmotokio.DTO.UserDto;
import com.filmotokio.model.User;
import com.filmotokio.model.UserRole;
import com.filmotokio.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthService(UserImpl userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    public String register(RegisterRequest request) {
        UserDto user = new UserDto();
        user.setName(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.save(user);
        return jwtUtil.generateToken(user.getEmail());
    }

    public String login(LoginRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return jwtUtil.generateToken(request.getUsername());
    }
}

