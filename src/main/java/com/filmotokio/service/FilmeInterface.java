package com.filmotokio.service;

import com.filmotokio.DTO.FilmDto;
import com.filmotokio.model.Film;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FilmeInterface {

    public List<Film> getAll();
    public Film save(FilmDto film) throws IOException;
    public Optional<Film> findById(Long id);
    public Boolean deleteById(Long id);
}
