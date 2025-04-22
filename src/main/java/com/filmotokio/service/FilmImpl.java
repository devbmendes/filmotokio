package com.filmotokio.service;

import com.filmotokio.DTO.FilmDto;
import com.filmotokio.model.Film;
import com.filmotokio.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilmImpl implements FilmeInterface{

    private final String uploadDir = "src/main/resources/static/uploads/film/";
    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film save(FilmDto filmDto) throws IOException {
            Film film = new Film();
            film.setTitle(filmDto.getTitulo());
            film.setDuration(film.getDuration());
            film.setYear(filmDto.getDuracao());

        MultipartFile multipartFile = filmDto.getCartaz();
        if(multipartFile!=null && !multipartFile.isEmpty()){
            String fileName = UUID.randomUUID()   + "_" + multipartFile.getOriginalFilename();
            Path url = Paths.get(uploadDir,fileName);
            Files.createDirectories(url.getParent());
            Files.write(url,multipartFile.getBytes());

            film.setPoster(uploadDir + fileName);
        }

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
