package com.filmotokio.service;

import com.filmotokio.model.Cast;
import com.filmotokio.model.Film;

import java.util.List;
import java.util.Optional;

public interface CastInterface {
    public List<Cast> getAll();
    public Cast save(Cast cast);
    public Optional<Cast> findById(Long id);
    public Boolean deleteById(Long id);
}
