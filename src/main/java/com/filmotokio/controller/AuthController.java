package com.filmotokio.controller;

import com.filmotokio.DTO.UserDto;
import com.filmotokio.service.UserImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserImpl userImpl;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model,
                        Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/"; // já logado, vai pra home
        }

        if (error != null) {
            model.addAttribute("erroLogin", "Email ou Password inválidos.");
        }

        if (logout != null) {
            model.addAttribute("mensagemLogout", "Você saiu com sucesso.");
        }

        return "/auth/login"; // <-- mostra a página de login corretamente
    }

    @GetMapping("/register")
    public String user(Model model){

        model.addAttribute("user",new UserDto());
        return "/auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) throws IOException {
        if(result.hasErrors()){
            return "/auth/register";
        }
        if (!userDto.getPassword().equals(userDto.getPasswordMatch())) {
            result.rejectValue("passwordMatch", null, "As password não coincidem");
            return "/auth/register";
        }
        userImpl.save(userDto);
        return "redirect:/auth/login";
    }
}
