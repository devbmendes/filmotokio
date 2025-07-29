package com.filmotokio.service;

import com.filmotokio.DTO.FilmDto;
import com.filmotokio.model.Film;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FilmeInterface {

    public List<FilmDto> getAll();
    public Film save(FilmDto film) throws IOException;
    public Optional<Film> findById(Long id);
    public Boolean deleteById(Long id);
    public List<Film> findByTitleContainingIgnoreCase(String title);
    public Film update(Long id,FilmDto filmDto);
    public List<Film> findNewFilmsForToday();
}
