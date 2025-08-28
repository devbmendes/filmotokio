package com.filmotokio.controller;

import com.filmotokio.DTO.UserDto;
import com.filmotokio.model.User;
import com.filmotokio.service.UserImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserImpl userImpl;


    @GetMapping("/all")
    public String getAll(Model model){
        List<User> users = userImpl.getAll();
        model.addAttribute("user",users);
        return "userList";
    }
    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            userImpl.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Utilizador deletado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao deletar o Utilizador.");
        }
        return "redirect:/admin/dashboard";

    }
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id,Model model){
        Optional<User> user = userImpl.findById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "userDetail";
    }

}
