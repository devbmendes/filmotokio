package com.filmotokio.controller;

import com.filmotokio.DTO.UserDto;
import com.filmotokio.model.User;
import com.filmotokio.model.UserRole;
import com.filmotokio.repository.UserRepository;
import com.filmotokio.service.CastImpl;
import com.filmotokio.service.FilmImpl;
import com.filmotokio.service.ReviewImpl;
import com.filmotokio.service.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/dashboard")
    public String adminDashboard(Model model,@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size,
                                 @RequestParam(required = false) String keyword) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        Page<User> users;
        if (keyword != null && !keyword.trim().isEmpty()) {
            users = userRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    keyword, keyword, keyword, pageable);
            model.addAttribute("keyword", keyword);
        } else {
            users = userRepository.findAll(pageable);
        }
        model.addAttribute("totalUsers", userImpl.userCount());
        model.addAttribute("totalFilms", filmImpl.filmCount());
        model.addAttribute("totalReviews", reviewImpl.reviewCount());
        model.addAttribute("totalCasts", castImpl.castCount());

        model.addAttribute("users",users);
        return "adminDashboard";
    }
    @GetMapping("/user/add")
    public String userRegister(Model model){
        model.addAttribute("user",new UserDto());
        return "userRegister";
    }
    @GetMapping("/user/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userImpl.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilizador inválido: " + id));
        model.addAttribute("user", user);
        model.addAttribute("roles", UserRole.values()); // 👈 popula o select dinamicamente
        return "user-update"; // página HTML para edição
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("user") User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilizador inválido: " + id));

        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setEmail(updatedUser.getEmail());
        user.setBirthDate(updatedUser.getBirthDate());
        // se quiseres atualizar a password:
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

        userRepository.save(user);
        return "redirect:/user"; // volta à lista
    }

}
