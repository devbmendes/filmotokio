package com.filmotokio.service;

import com.filmotokio.DTO.REST.LoginRequest;
import com.filmotokio.DTO.REST.RegisterRequest;
import com.filmotokio.DTO.REST.RegisterResponse;
import com.filmotokio.DTO.UserDto;
import com.filmotokio.exception.ResourceNotFoundException;
import com.filmotokio.jwt.JwtUtil;
import com.filmotokio.model.User;
import com.filmotokio.model.UserRole;
import com.filmotokio.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    private final UserImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final MyUserDetailsService userDetailsService;
    private final UserRepository userRepository;

    public AuthService(UserImpl userService,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       AuthenticationManager authManager,
                       MyUserDetailsService userDetailsService, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    public RegisterResponse register(RegisterRequest request) {
        Optional<User> userRequest = userRepository.findByEmail(request.getEmail());
        if (userRequest.isPresent()){
            throw new RuntimeException("Esse email ja existe");
        }
        User user = new User();
        user.setName(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRegistrationDate(new Date());
        User userSave = userRepository.save(user);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUsername(userSave.getEmail());
        registerResponse.setRole(userSave.getRole().toString());


        return registerResponse;
    }

    public String login(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        Long userId = userService.findByEmail(request.getUsername()).orElseThrow(
                ()-> new ResourceNotFoundException("User",null)
        ).getId();

        return jwtUtil.generateToken(userDetails, userId);
    }
}
