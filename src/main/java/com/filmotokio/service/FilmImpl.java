package com.filmotokio.service;

import com.filmotokio.DTO.FilmDto;
import com.filmotokio.exception.DatabaseException;
import com.filmotokio.exception.ResourceNotFoundException;
import com.filmotokio.model.Cast;
import com.filmotokio.model.Film;
import com.filmotokio.model.PersonType;
import com.filmotokio.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

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
    public List<FilmDto> getAll() {
        List<FilmDto> filmDtos = new ArrayList<>();
        List<Film> filmList = filmRepository.findAll();
        for (Film films : filmList){
            FilmDto filmDto = new FilmDto();
            filmDto.setTitle(films.getTitle());
            filmDto.setYear(films.getYear());
            filmDtos.add(filmDto);
        }
        return filmDtos;
    }

    @Override
    public Film save(FilmDto filmDto) throws IOException {
            Film film = new Film();
            film.setTitle(filmDto.getTitle());
            film.setDuration(filmDto.getDuration());
            film.setYear(filmDto.getYear());
            film.setSynopsis(filmDto.getSynopsis());
            film.setTeaserYoutubeCode(filmDto.getTeaserYoutubeCode());
            film.setCreatedAt(LocalDate.now());
        List<Long> allCastIds = getAllCastIds(filmDto);
        List<Cast> elencos = new ArrayList<>();
        for (Long id : allCastIds) {
            castImpl.findById(id).ifPresent(elencos::add);
        }
    film.setElenco(elencos);

        MultipartFile multipartFile = filmDto.getPoster();
        if(multipartFile!=null && !multipartFile.isEmpty()){
            String fileName = UUID.randomUUID()   + "_" + multipartFile.getOriginalFilename();
            Path url = Paths.get(uploadDir,fileName);
            Files.createDirectories(url.getParent());
            Files.write(url,multipartFile.getBytes());

            film.setPoster("/uploads/film/" + fileName);
        }
        try {
           Film filmSaved = filmRepository.save(film);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro ao salvar no banco: dados inválidos");
        }
        return film;
    }

    @Override
    public Optional<Film> findById(Long id) {

        return Optional.ofNullable(filmRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Film", id)));
    }

    @Override
    public Boolean deleteById(Long id) {
        return findById(id).isPresent();
    }

    @Override
    public List<Film> findByTitleContainingIgnoreCase(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Film update(Long id, FilmDto filmDto) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isPresent()){
            film.get().setTitle(filmDto.getTitle());
            film.get().setSynopsis(filmDto.getSynopsis());
            film.get().setTitle(filmDto.getTitle());
            film.get().setDuration(filmDto.getDuration());
            return filmRepository.save(film.get());
        }else {
            return new Film();
        }

    }
    @Override
    public List<Film> findNewFilmsForToday() {
        LocalDate today = LocalDate.now();
        return filmRepository.findByCreatedAt(today);
    }

    @Override
    public Long filmCount() {
        return filmRepository.count();
    }
}
