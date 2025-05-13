package com.filmotokio.repository;

import com.filmotokio.model.Cast;
import com.filmotokio.model.PersonType;
import org.hibernate.query.sqm.CastType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CastRepository extends JpaRepository<Cast,Long> {

    List<Cast> findByType(PersonType type);
    Optional<Cast> findByEmailIgnoreCase(String email);
    List<Cast> findByFilmsIdAndType(Long filmId, PersonType type);
}
