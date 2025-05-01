package com.filmotokio.controller;

import com.filmotokio.DTO.UserDto;
import com.filmotokio.service.UserImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserImpl userImpl;

    @GetMapping
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String user(Model model){

        model.addAttribute("user",new UserDto());
        return "userAdd";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) throws IOException {
        if(result.hasErrors()){
            return "userAdd";
        }
        if (!userDto.getPassword().equals(userDto.getPasswordMatch())) {
            result.rejectValue("passwordMatch", null, "As password não coincidem");
            return "userAdd";
        }
        userImpl.save(userDto);

        return "redirect:/auth/login";
    }
}
