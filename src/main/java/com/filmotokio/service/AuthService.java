package com.filmotokio.service;

import com.filmotokio.DTO.REST.LoginRequest;
import com.filmotokio.DTO.REST.RegisterRequest;
import com.filmotokio.DTO.UserDto;
import com.filmotokio.model.User;
import com.filmotokio.model.UserRole;
import com.filmotokio.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final MyUserDetailsService userDetailsService;

    public AuthService(UserImpl userService,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       AuthenticationManager authManager,
                       MyUserDetailsService userDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
    }

    public String register(RegisterRequest request) {
        UserDto user = new UserDto();
        user.setName(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.save(user);

        // Pega UserDetails e ID
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        Long userId = userService.findByEmail(user.getEmail()).get().getId();

        return jwtUtil.generateToken(userDetails, userId);
    }

    public String login(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        Long userId = userService.findByEmail(request.getUsername()).get().getId();

        return jwtUtil.generateToken(userDetails, userId);
    }
}
