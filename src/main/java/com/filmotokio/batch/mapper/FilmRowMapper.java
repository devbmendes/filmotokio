package com.filmotokio.batch.mapper;

import com.filmotokio.model.Film;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FilmRowMapper implements RowMapper<Film> {

    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        Film film = new Film();
        film.setId(rs.getLong("id"));
        film.setTitle(rs.getString("title"));
        film.setYear(rs.getInt("year"));
        film.setDuration(rs.getInt("duration"));
        film.setSynopsis(rs.getString("synopsis"));
        film.setPoster(rs.getString("poster"));

        // Tratamento para LocalDate
        film.setCreatedAt(rs.getObject("createdAt", LocalDate.class));
        // Tratamento para migration que pode ser nulo
        film.setMigration(rs.getObject("migration", LocalDate.class));
        return film;
    }
}
