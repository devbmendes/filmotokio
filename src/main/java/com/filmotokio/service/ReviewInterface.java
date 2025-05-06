package com.filmotokio.service;

import com.filmotokio.DTO.ReviewDto;
import com.filmotokio.model.Film;
import com.filmotokio.model.Review;
import com.filmotokio.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewInterface {
    List<Review> findFilmByUserId(Long id);
    Optional<Review> findByUserAndFilm(User user, Film film);
    List<Review> findByFilmId(Long filmId);
    Review save(ReviewDto reviewDto);
}
