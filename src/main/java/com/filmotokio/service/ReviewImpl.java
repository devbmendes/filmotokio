package com.filmotokio.service;

import com.filmotokio.DTO.ReviewDto;
import com.filmotokio.DTO.ReviewListDto;
import com.filmotokio.exception.ResourceNotFoundException;
import com.filmotokio.model.Film;
import com.filmotokio.model.Review;
import com.filmotokio.model.User;
import com.filmotokio.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public List<ReviewListDto> findByFilmId(Long filmId) {
        List<ReviewListDto> listDtos = new ArrayList<>();
        List<Review> reviewList = reviewRepository.findByFilmId(filmId);
        SimpleDateFormat sdf = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
             for (Review review : reviewList) {
                 // Formata a data
                 String dataFormatada = sdf.format(review.getDate());
                 // Capitaliza a primeira letra (ex: "maio" -> "Maio")
                 ReviewListDto reviewListDto = new ReviewListDto(
                         review.getUser().getName()+" "+review.getUser().getSurname(),
                         dataFormatada,review.getTextReview(),review.getRating());
                 listDtos.add(reviewListDto);
             }
           return  listDtos;
        }

    @Override
    public Review save(ReviewDto reviewDto) {
        User user = userImpl.findById(reviewDto.getUserId()).orElseThrow(()
                -> new ResourceNotFoundException("User",reviewDto.getUserId()));

        Film film = filmImpl.findById(reviewDto.getFilmId()).orElseThrow(
                ()-> new ResourceNotFoundException("Film",reviewDto.getFilmId()));

        Optional<Review> review = findByUserAndFilm(user,film);
        if (review.isEmpty()){
            Review newReview = new Review(new Date(),reviewDto.getFilmReview(),user,film,reviewDto.getRating());
            System.out.println(newReview.getRating());
            return reviewRepository.save(newReview);
        }
        return new Review();
    }

    @Override
    public List<ReviewDto> findByUser(User user) {
        User findUser = userImpl.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", user.getId()));

        List<Review> reviews = reviewRepository.findByUser(findUser);

        return reviews.stream().map(this::toDto).toList();
    }
    private ReviewDto toDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setFilmId(review.getFilm().getId());
        dto.setUserId(review.getUser().getId());
        dto.setFilmReview(review.getTextReview());
        dto.setRating(review.getRating());
        return dto;
    }

}
