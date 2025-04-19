package com.filmotokio.controller;

import com.filmotokio.DTO.CastDto;
import com.filmotokio.service.CastImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cast")
public class CastController {
    @Autowired
    private CastImpl castImpl;

    @GetMapping("")
    public String cast(Model model){
        model.addAttribute("cast", new CastDto());
        return "castAdd";
    }

    @PostMapping("/save")
    public String save(CastDto castDto){
        castImpl.save(castDto);
        return "";
    }
}
