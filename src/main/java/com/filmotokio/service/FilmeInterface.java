package com.filmotokio.service;

import com.filmotokio.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmeInterface {

    public List<Film> getAll();
    public Film save(Film film);
    public Optional<Film> findById(Long id);
    public Boolean deleteById(Long id);
}
