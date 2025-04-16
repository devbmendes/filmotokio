package com.filmotokio.service;

import com.filmotokio.model.Cast;
import com.filmotokio.model.Film;
import com.filmotokio.repository.CastRepository;
import com.filmotokio.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CastImpl implements CastInterface{
    @Autowired
    private CastRepository castRepository;

    @Override
    public List<Cast> getAll() {
        return castRepository.findAll();
    }

    @Override
    public Cast save(Cast cast) {
        return castRepository.save(cast);
    }

    @Override
    public Optional<Cast> findById(Long id) {
        return Optional.of(castRepository.findById(id).orElseThrow());
    }

    @Override
    public Boolean deleteById(Long id) {
        return findById(id).isPresent();
    }
}
