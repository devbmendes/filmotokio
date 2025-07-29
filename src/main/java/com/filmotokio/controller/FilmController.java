package com.filmotokio.controller;

import com.filmotokio.DTO.FilmDto;
import com.filmotokio.DTO.ReviewDto;
import com.filmotokio.model.Film;
import com.filmotokio.model.User;
import com.filmotokio.repository.FilmRepository;
import com.filmotokio.service.CastImpl;
import com.filmotokio.service.FilmImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmImpl filmImpl;

    @Autowired
    private CastImpl castImpl;

    @Autowired
    FilmRepository filmRepository;

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

    @PostMapping
    public String save(@ModelAttribute("film") @Valid FilmDto filmDto, BindingResult result,
                       Model model, RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()){
            model.addAttribute("diretores", castImpl.findByType("DIRECTOR"));
            model.addAttribute("roteiristas", castImpl.findByType("WRITER"));
            model.addAttribute("musicos", castImpl.findByType("MUSICIAN"));
            model.addAttribute("atores", castImpl.findByType("ACTOR"));
            model.addAttribute("fotografos", castImpl.findByType("PHOTOGRAPHER"));

            return "filmAdd";
        }
        filmImpl.save(filmDto);
        redirectAttributes.addFlashAttribute("successMessage","Filme adicionado com sucesso");
        return "redirect:/film/all";
    }


    @GetMapping("/all")
    public String listarFilm(@RequestParam(required = false)String title, Model model) {
        String path = "http://localhost:8080/uploads/film/";
        List<Film> film;
        if (title!= null && !title.isBlank()){
            film = filmImpl.findByTitleContainingIgnoreCase(title);
        }else {
            film = filmRepository.findAll();
        }

        model.addAttribute("path",path);
        model.addAttribute("film",film);

        return "filmSearch";
    }
    @PostMapping("/update/{id}")
    public String update(FilmDto filmDto,@PathVariable Long id){
        return "redirect:/film/all";
    }

    @GetMapping("/list")
    public String getAll(Model model){
        List<FilmDto> filmList = filmImpl.getAll();
        if (filmList.isEmpty()){
            model.addAttribute("emptyList","Lista de Filme vazia");
        }
        System.out.println(filmList);
        model.addAttribute("films",filmList);
        return "filmList";
    }



}
