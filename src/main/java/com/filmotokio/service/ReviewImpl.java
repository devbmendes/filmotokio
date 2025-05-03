package com.filmotokio.service;

import com.filmotokio.DTO.ReviewDto;
import com.filmotokio.exception.ResourceNotFoundException;
import com.filmotokio.exception.ReviewExistException;
import com.filmotokio.model.Review;
import com.filmotokio.model.User;
import com.filmotokio.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ReviewImpl implements ReviewInterface{
    
    @Autowired
    private UserImpl userImpl;
    @Autowired
    ReviewRepository reviewRepository;

    private List<Review> findByUserId(Long id) {

        if(reviewRepository.findByUserId(id).isEmpty()){
            throw new ResourceNotFoundException("User",id);
        }else {
            return reviewRepository.findByUserId(id);
        }
    }

    @Override
    public Optional<Review> findByUserIdAndFilmId(ReviewDto reviewDto) {
        System.out.println(reviewDto);
        Optional<Review> existReview = reviewRepository.findByUserIdAndFilmId(reviewDto.getUserId(),
                reviewDto.getFilmId());
        if (existReview.isPresent()){
            throw new ReviewExistException("O utilizador ja avaliou esse film");
        }
        return existReview;
    }
}
