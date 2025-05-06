package com.filmotokio.controller;

import com.filmotokio.DTO.ReviewDto;
import com.filmotokio.exception.ResourceNotFoundException;
import com.filmotokio.model.Film;
import com.filmotokio.model.Review;
import com.filmotokio.model.User;
import com.filmotokio.service.FilmImpl;
import com.filmotokio.service.ReviewImpl;
import com.filmotokio.service.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewImpl reviewImpl;
    @Autowired
    UserImpl userImpl;
    @Autowired
    FilmImpl filmImpl;

    @GetMapping("/{filmId}")
    public String getById(@PathVariable Long filmId, Model model, Principal principal) throws NoSuchFieldException {

        Film film = filmImpl.findById(filmId).orElseThrow(
                () -> new ResourceNotFoundException("Film", filmId));
        User user = userImpl.findByEmail(principal.getName()).orElseThrow(
                ()-> new ResourceNotFoundException("User",0L));


        Optional<Review> filmReviews = reviewImpl.findByUserAndFilm(user, film);
        List<Review> reviewList = reviewImpl.findByFilmId(filmId);

        model.addAttribute("film", film);
        model.addAttribute("allReviews", reviewList);
        model.addAttribute("user",user);

        if (filmReviews.isEmpty()) {
            model.addAttribute("review", new Review()); // para o form de novo review
            model.addAttribute("showForm", true);
        } else {
            model.addAttribute("showForm", false);
            model.addAttribute("userReview", filmReviews.get()); // opcional: exibe review atual do user
        }

        return "filmID";
    }


    @PostMapping("/save")
    public String findByUserId(@ModelAttribute ReviewDto reviewDto)  {

        return "redirect:/film/all";
    }
}
