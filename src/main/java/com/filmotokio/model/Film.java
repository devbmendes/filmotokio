package com.filmotokio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int year;
    private int duration;
    private String synopsis;
    private String poster;
    private boolean migrate;
    private Date dateMigrate;

    @OneToMany(mappedBy = "film")
    private List<Review> reviews;

    @OneToMany(mappedBy = "film")
    private List<Score> scores;

    @ManyToMany
    @JoinTable(
            name = "film_cast",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id")
    )
    private List<Cast> crew;
}

