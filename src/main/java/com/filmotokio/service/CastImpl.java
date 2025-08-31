package com.filmotokio.service;

import com.filmotokio.DTO.CastDto;
import com.filmotokio.model.Cast;
import com.filmotokio.model.Film;
import com.filmotokio.model.PersonType;
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
    public Cast save(CastDto castDto) {
        Cast cast = new Cast(castDto.getName(),castDto.getSurname(),castDto.getEmail(), PersonType.valueOf(castDto.getType()));
        return castRepository.save(cast);
    }
    @Override
    public List<Cast> getDirectoresByFilm(Long filmId,PersonType type) {
        return castRepository.findByFilmsIdAndType(filmId, type);
    }

    @Override
    public Long castCount() {
        return castRepository.count();
    }

    @Override
    public Optional<Cast> findById(Long id) {

        return Optional.of(castRepository.findById(id).orElseThrow());
    }

    @Override
    public Boolean deleteById(Long id) {
        return findById(id).isPresent();
    }

    @Override
    public List<Cast> findByType(String type) {
        PersonType personType = PersonType.valueOf(type.toUpperCase());
        return castRepository.findByType(personType);
    }

    @Override
    public boolean findByEmail(String email) {
        return (castRepository.findByEmailIgnoreCase(email).isPresent());
    }
}
