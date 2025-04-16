package com.filmotokio.service;

import com.filmotokio.model.Film;
import com.filmotokio.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FilmImpl implements FilmeInterface{

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film save(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public Optional<Film> findById(Long id) {
        return Optional.of(filmRepository.findById(id).orElseThrow());
    }

    @Override
    public Boolean deleteById(Long id) {
        return findById(id).isPresent();
    }
}
