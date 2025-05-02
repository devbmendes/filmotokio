package com.filmotokio.service;

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
        List<Review> reviewList = reviewRepository.findByUserId(id);
        if (reviewList.isEmpty()){
            return new ArrayList<>();
        }else {
            return reviewList;
        }

    }
}
