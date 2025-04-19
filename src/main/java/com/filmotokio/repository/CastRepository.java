package com.filmotokio.repository;

import com.filmotokio.model.Cast;
import com.filmotokio.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastRepository extends JpaRepository<Cast,Long> {

    public List<Cast> findByType(PersonType type);
}
