package com.filmotokio.service;

import com.filmotokio.DTO.ReviewDto;
import com.filmotokio.model.Review;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewInterface {
    Optional<Review> findByUserIdAndFilmId(ReviewDto reviewDto);
}
