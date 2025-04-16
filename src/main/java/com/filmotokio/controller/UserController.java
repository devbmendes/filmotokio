package com.filmotokio.controller;

import com.filmotokio.model.User;
import com.filmotokio.service.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("allUsers",users);
        return "allUsers";
    }
    @PostMapping("save")
    public String saveUser(User user){
        userImpl.save(user);
        return "redirect:/user/all";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        userImpl.deleteById(id);
        return "redrect:/user/all";
    }
    @GetMapping("/get/{id}")
    public String getById(@PathVariable Long id,Model model){
        Optional<User> user = userImpl.findById(id);
        model.addAttribute("user",user);
        return "singleUser";
    }

}
