package com.filmotokio.controller;

import com.filmotokio.DTO.UserDto;
import com.filmotokio.model.User;
import com.filmotokio.service.UserImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserImpl userImpl;

    @GetMapping
    public String user(Model model){

        model.addAttribute("user",new UserDto());
        return "userAdd";
    }
    @GetMapping("/all")
    public String getAll(Model model){
        List<User> users = userImpl.getAll();
        model.addAttribute("user",users);
        return "userList";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) throws IOException {
        if(result.hasErrors()){
            return "userAdd";
        }
        if (!userDto.getPassword().equals(userDto.getPasswordMatch())) {
            result.rejectValue("passwordMatch", null, "As password não coincidem");
            return "userAdd";
        }
        userImpl.save(userDto);

        return "redirect:/";
    }
    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            userImpl.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Utilizador deletado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao deletar o Utilizador.");
        }
        return "redirect:/user/all";

    }
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id,Model model){
        Optional<User> user = userImpl.findById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "userDetail";
    }

}
