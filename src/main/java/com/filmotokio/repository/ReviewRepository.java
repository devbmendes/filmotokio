package com.filmotokio.repository;

import com.filmotokio.model.Film;
import com.filmotokio.model.Review;
import com.filmotokio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByUser(User user);
    Optional<Review> findByUserAndFilm(User user, Film film);
    List<Review> findByFilmId(Long filmId);
    List<Review> findByUserId(Long userId);
}
