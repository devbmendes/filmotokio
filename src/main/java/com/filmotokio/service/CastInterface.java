package com.filmotokio.service;

import com.filmotokio.DTO.CastDto;
import com.filmotokio.model.Cast;
import com.filmotokio.model.Film;
import com.filmotokio.model.PersonType;

import java.util.List;
import java.util.Optional;

public interface CastInterface {
    public List<Cast> getAll();
    public Cast save(CastDto castDto);
    public Optional<Cast> findById(Long id);
    public Boolean deleteById(Long id);
    public List<Cast> findByType(String type);
    public boolean findByEmail(String email);
    public List<Cast> getDirectoresByFilm(Long filmId, PersonType type);
}
