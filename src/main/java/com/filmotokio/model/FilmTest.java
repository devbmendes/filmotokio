package com.filmotokio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "film_test")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmTest {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private Integer release_year;

        private LocalDate created_at;
        private LocalDate migration;
    }

