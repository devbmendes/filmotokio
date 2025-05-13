package com.filmotokio.controller;

import com.filmotokio.DTO.ReviewDto;
import com.filmotokio.DTO.ReviewListDto;
import com.filmotokio.exception.ResourceNotFoundException;
import com.filmotokio.model.Film;
import com.filmotokio.model.PersonType;
import com.filmotokio.model.Review;
import com.filmotokio.model.User;
import com.filmotokio.service.CastImpl;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewImpl reviewImpl;
    @Autowired
    UserImpl userImpl;
    @Autowired
    FilmImpl filmImpl;
    @Autowired
    CastImpl castImpl;

    @GetMapping("/{filmId}")
    public String getById(@PathVariable Long filmId, Model model, Principal principal) throws NoSuchFieldException {

        Film film = filmImpl.findById(filmId).orElseThrow(
                () -> new ResourceNotFoundException("Film", filmId));
        User user = userImpl.findByEmail(principal.getName()).orElseThrow(
                ()-> new ResourceNotFoundException("User",0L));

        model.addAttribute("diretores",castImpl.getDirectoresByFilm(filmId,PersonType.DIRECTOR));
        model.addAttribute("atores",castImpl.getDirectoresByFilm(filmId,PersonType.ACTOR));

        Optional<Review> filmReviews = reviewImpl.findByUserAndFilm(user, film);
        List<ReviewListDto> reviewDTOs = reviewImpl.findByFilmId(filmId);

        model.addAttribute("film", film);
        model.addAttribute("allReviews", reviewDTOs);
        model.addAttribute("user",user);

        if (filmReviews.isEmpty()) {
            model.addAttribute("showForm", true);
            model.addAttribute("review", new ReviewDto()); // para o form de novo review
        } else {
            model.addAttribute("showForm", false);
        }


        return "filmID";
    }


    @PostMapping("/save")
    public String findByUserId(@ModelAttribute ReviewDto reviewDto,Principal principal)  {
        if (reviewDto.getRating() < 0 || reviewDto.getRating() > 5){
            throw new IllegalArgumentException("Insira uma nota valida");
        }
        User user = userImpl.findByEmail(principal.getName()).orElseThrow(
                ()-> new ResourceNotFoundException("User",0L));
        reviewDto.setUserId(user.getId());
        Long filmId = reviewDto.getFilmId();
        reviewImpl.save(reviewDto);


        return "redirect:/review/"+filmId;
    }
}
