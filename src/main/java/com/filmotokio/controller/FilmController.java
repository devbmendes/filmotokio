package com.filmotokio.controller;

import com.filmotokio.DTO.FilmDto;
import com.filmotokio.model.Film;
import com.filmotokio.service.CastImpl;
import com.filmotokio.service.FilmImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmImpl filmImpl;

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
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id,Model model){
        Film film = filmImpl.findById(id).orElse(null);
        model.addAttribute("film",film);
        return "filmID";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("film") @Valid FilmDto filmDto, BindingResult result,
                       Model model) throws IOException {
        if (result.hasErrors()){
            model.addAttribute("diretores", castImpl.findByType("DIRECTOR"));
            model.addAttribute("roteiristas", castImpl.findByType("WRITER"));
            model.addAttribute("musicos", castImpl.findByType("MUSICIAN"));
            model.addAttribute("atores", castImpl.findByType("ACTOR"));
            model.addAttribute("fotografos", castImpl.findByType("PHOTOGRAPHER"));
            return "filmAdd";
        }
        filmImpl.save(filmDto);
        return "redirect:/film/search";
    }


    @GetMapping("/search")
    public String listarFilm(@RequestParam(required = false)String title, Model model) {
        String path = "http://localhost:8080/uploads/film/";
        List<Film> film;
        if (title!= null && !title.isBlank()){
            film = filmImpl.findByTitleContainingIgnoreCase(title);
        }else {
            film = filmImpl.getAll();
        }

        model.addAttribute("path",path);
        model.addAttribute("film",film);
        return "filmSearch";
    }


}
