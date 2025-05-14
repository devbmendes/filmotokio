package com.filmotokio.batch.writer;

import com.filmotokio.model.Film;
import com.filmotokio.repository.FilmRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.time.LocalDate;
import java.util.List;


public class FilmWriteListener implements ItemWriteListener<Film>, FilmWriteListenerIn {

    private static final Logger logger = LoggerFactory.getLogger(FilmWriteListener.class);
    private final FilmRepository filmRepository;

    public FilmWriteListener(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
    @Override
    public void beforeWrite(List<? extends Film> items) {
        logger.info("Preparando para escrever {} filmes no CSV", items.size());
    }
    @Override
    public void afterWrite(List<? extends Film> items) {
        items.forEach(film -> {
            film.setMigration(LocalDate.now());
            filmRepository.save(film);
            logger.info("Filme {} marcado como migrado em {}", film.getTitle(), film.getMigration());
        });
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Film> items) {
        logger.error("Erro ao escrever filmes no CSV", exception);
    }


}