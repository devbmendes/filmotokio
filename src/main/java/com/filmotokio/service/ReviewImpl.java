package com.filmotokio.service;

import com.filmotokio.exception.ResourceNotFoundException;
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
    @Override
    public List<Review> findByUserId(Long id) {

        if(reviewRepository.findByUserId(id).isEmpty()){
            throw new ResourceNotFoundException("User",id);
        }else {
            return reviewRepository.findByUserId(id);
        }
    }
}
