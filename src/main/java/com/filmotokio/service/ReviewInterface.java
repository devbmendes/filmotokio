package com.filmotokio.service;

import com.filmotokio.model.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewInterface {
    public List<Review> findByUserId(Long id);
}
