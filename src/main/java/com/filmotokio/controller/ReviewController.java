package com.filmotokio.controller;

import com.filmotokio.DTO.ReviewDto;
import com.filmotokio.model.Review;
import com.filmotokio.model.User;
import com.filmotokio.service.ReviewImpl;
import com.filmotokio.service.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewImpl reviewImpl;
    @Autowired
    UserImpl userImpl;

    @PostMapping("/save")
    public String findByUserId(@ModelAttribute ReviewDto reviewDto)  {
        reviewImpl.findByUserIdAndFilmId(reviewDto);

        return "redirect:/film/all";
    }
}
