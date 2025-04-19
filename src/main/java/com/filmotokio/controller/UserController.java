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
        model.addAttribute("allUsers",users);
        return "allUsers";
    }
    @PostMapping("save")
    public String saveUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result){
        System.out.println(userDto.toString());
        if (!userDto.getPassword().equals(userDto.getPasswordMatch())) {
            System.out.println("Aqui");
            result.rejectValue("passwordMatch", null, "As password não coincidem");
        }
        if (result.hasErrors()) {
            System.out.println("erro");
            return "userAdd";
        }
        userImpl.save(userDto);
        System.out.println("salvei");
        return "userAdd";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        userImpl.deleteById(id);
        return "redirect:/user/all";
    }
    @GetMapping("/get/{id}")
    public String getById(@PathVariable Long id,Model model){
        Optional<User> user = userImpl.findById(id);
        model.addAttribute("user",user);
        return "singleUser";
    }

}
