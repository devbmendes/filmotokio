package com.filmotokio.service;

import com.filmotokio.DTO.FilmDto;
import com.filmotokio.model.Cast;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilmImpl implements FilmeInterface{

    public final String uploadDir = "uploads/film";

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private CastImpl castImpl;

    private List<Long> getAllCastIds(FilmDto filmDto) {
        List<Long> allCastIds = new ArrayList<>();

        if (filmDto.getDiretoresIds() != null) {
            allCastIds.addAll(filmDto.getDiretoresIds());
        }
        if (filmDto.getRoteiristasIds() != null) {
            allCastIds.addAll(filmDto.getRoteiristasIds());
        }
        if (filmDto.getMusicosIds() != null) {
            allCastIds.addAll(filmDto.getMusicosIds());
        }
        if (filmDto.getAtoresIds() != null) {
            allCastIds.addAll(filmDto.getAtoresIds());
        }
        if (filmDto.getFotografoId() != null) {
            allCastIds.add(filmDto.getFotografoId());
        }

        return allCastIds;
    }


    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film save(FilmDto filmDto) throws IOException {
            Film film = new Film();
            film.setTitle(filmDto.getTitle());
            film.setDuration(filmDto.getDuration());
            film.setYear(filmDto.getYear());
            film.setSynopsis(filmDto.getSynopsis());
        List<Long> allCastIds = getAllCastIds(filmDto);
        System.out.println(allCastIds);
        List<Cast> elencos = new ArrayList<>();
        for (Long id : allCastIds) {
            castImpl.findById(id).ifPresent(elencos::add);
        }
    film.setCrew(elencos);

        MultipartFile multipartFile = filmDto.getPoster();
        if(multipartFile!=null && !multipartFile.isEmpty()){
            String fileName = UUID.randomUUID()   + "_" + multipartFile.getOriginalFilename();
            Path url = Paths.get(uploadDir,fileName);
            Files.createDirectories(url.getParent());
            Files.write(url,multipartFile.getBytes());

            film.setPoster("/uploads/film/" + fileName);
        }
        System.out.println(film.toString());
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

    @Override
    public List<Film> findByTitleContainingIgnoreCase(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title);
    }
}
