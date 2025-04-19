package com.filmotokio.controller;

import com.filmotokio.DTO.FilmDto;
import com.filmotokio.service.CastImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private CastImpl castImpl;
    @GetMapping
    public String film(Model model){
        model.addAttribute("film", new FilmDto());
        model.addAttribute("diretores", castImpl.findByType("DIRECTOR"));
        model.addAttribute("roteiristas", castImpl.findByType("WRITER"));
        model.addAttribute("musicos", castImpl.findByType("MUSICIAN"));
        model.addAttribute("atores", castImpl.findByType("ACTOR"));
        model.addAttribute("fotografos", castImpl.findByType("PHOTOGRAPHER"));
        return "filmAdd";
    }

}
