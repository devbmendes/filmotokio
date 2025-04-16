package com.filmotokio.repository;

import com.filmotokio.model.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRepository extends JpaRepository<Cast,Long> {
}
