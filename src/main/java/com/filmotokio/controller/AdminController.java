package com.filmotokio.controller;

import com.filmotokio.DTO.UserDto;
import com.filmotokio.model.User;
import com.filmotokio.service.CastImpl;
import com.filmotokio.service.FilmImpl;
import com.filmotokio.service.ReviewImpl;
import com.filmotokio.service.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserImpl userImpl;
    @Autowired
    FilmImpl filmImpl;
    @Autowired
    ReviewImpl reviewImpl;
    @Autowired
    CastImpl castImpl;


    @GetMapping("/dashboard")
    public String adminDashboard(Model model,@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        Page<User> usersPage = userImpl.findAllUsers(page, size);
        model.addAttribute("usersPage", usersPage);
        model.addAttribute("totalUsers", userImpl.userCount());
        model.addAttribute("totalFilms", filmImpl.filmCount());
        model.addAttribute("totalReviews", reviewImpl.reviewCount());
        model.addAttribute("totalCasts", castImpl.castCount());

        model.addAttribute("user",usersPage);
        return "adminDashboard";
    }
    @GetMapping("/user/add")
    public String userRegister(Model model){
        model.addAttribute("user",new UserDto());
        return "userRegister";
    }
}
