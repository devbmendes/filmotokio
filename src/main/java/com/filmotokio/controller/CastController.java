package com.filmotokio.controller;

import com.filmotokio.DTO.CastDto;
import com.filmotokio.service.CastImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String save(@ModelAttribute("cast") @Valid CastDto castDto,
                       BindingResult result,Model model){
        System.out.println("Entrei");
        if (result.hasErrors()){
            System.out.println("ERRO");
            return "castAdd";
        }
        if (castImpl.findByEmail(castDto.getEmail())){
            System.out.println("EMAIL");
            result.rejectValue("email", "error.cast", "E-mail já existente!");
            return "castAdd";
        }
        castImpl.save(castDto);
        System.out.println("salvei");
        return "redirect:/cast";
    }
}
