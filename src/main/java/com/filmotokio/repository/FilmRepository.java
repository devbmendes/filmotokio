package com.filmotokio.repository;

import com.filmotokio.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {
    public List<Film> findByTitleContainingIgnoreCase(String title);
}
