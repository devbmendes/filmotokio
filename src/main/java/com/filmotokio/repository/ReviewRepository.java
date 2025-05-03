package com.filmotokio.repository;

import com.filmotokio.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByUserId(Long userId);
    Optional<Review> findByUserIdAndFilmId(Long userId,Long filmId);
}
