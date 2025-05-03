package com.filmotokio.controller;

import com.filmotokio.model.Review;
import com.filmotokio.model.User;
import com.filmotokio.service.ReviewImpl;
import com.filmotokio.service.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewImpl reviewImpl;
    @Autowired
    UserImpl userImpl;

    @GetMapping("/{userId}")
    public String findByUserId(@PathVariable Long userId, Model model) {
        List<Review> user = reviewImpl.findByUserId(userId);

        System.out.println(userId);
            List<Review> reviewList = reviewImpl.findByUserId(userId);
            model.addAttribute("user",user);
            model.addAttribute("reviews", reviewList);
        return "reviews";
    }
}
