package com.filmotokio.controller;

import com.filmotokio.DTO.ReviewDto;
import com.filmotokio.exception.ResourceNotFoundException;
import com.filmotokio.model.User;
import com.filmotokio.jwt.JwtUtil;
import com.filmotokio.service.ReviewImpl;
import com.filmotokio.service.UserImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Críticas", description = "Endpoints relacionados às críticas de filmes")
@RestController
@RequestMapping("/api/reviews")
public class ReviewRestController {

    @Autowired
    private ReviewImpl reviewService;
    @Autowired
    private  JwtUtil jwtUtil;
    @Autowired
    private UserImpl  userImpl;


    @GetMapping("/me")
    public ResponseEntity<List<ReviewDto>> getMyReviews(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);
        User user = userImpl.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User",userId)
        );
        List<ReviewDto> reviews = reviewService.findByUser(user);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody ReviewDto dto,
                                               @RequestHeader("Authorization") String authHeader) {
        System.out.println("DTO recebido: " + dto);
        String token = authHeader.substring(7); // remove "Bearer "
        Long userId = jwtUtil.extractUserId(token);
        dto.setUserId(userId);

        reviewService.save(dto);

        return ResponseEntity.ok("Crítica criada com sucesso");
    }
}

