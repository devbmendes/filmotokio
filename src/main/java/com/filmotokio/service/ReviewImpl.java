package com.filmotokio.service;

import com.filmotokio.DTO.ReviewDto;
import com.filmotokio.exception.ResourceNotFoundException;
import com.filmotokio.exception.ReviewExistException;
import com.filmotokio.model.Film;
import com.filmotokio.model.Review;
import com.filmotokio.model.User;
import com.filmotokio.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ReviewImpl implements ReviewInterface{
    
    @Autowired
    private UserImpl userImpl;
    @Autowired
    FilmImpl filmImpl;
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> findFilmByUserId(Long id) {

        if(reviewRepository.findByUserId(id).isEmpty()){
            throw new ResourceNotFoundException("User",id);
        }else {
            return reviewRepository.findByUserId(id);
        }
    }

    @Override
    public Optional<Review> findByUserAndFilm(User user, Film film) {
        return reviewRepository.findByUserAndFilm(user, film);
    }

    @Override
    public List<Review> findByFilmId(Long filmId) {
        return reviewRepository.findByFilmId(filmId);
    }

    @Override
    public Review save(ReviewDto reviewDto) {
        User user = userImpl.findById(reviewDto.getUserId()).orElseThrow(()
                -> new ResourceNotFoundException("User",reviewDto.getUserId()));

        Film film = filmImpl.findById(reviewDto.getFilmId()).orElseThrow(
                ()-> new ResourceNotFoundException("Film",reviewDto.getFilmId()));

        Optional<Review> review = findByUserAndFilm(user,film);
        if (review.isEmpty()){
            Review newReview = new Review(new Date(),reviewDto.getFilmReview(),user,film);
            return reviewRepository.save(newReview);
        }
        return new Review();
    }

}
