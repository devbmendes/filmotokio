package com.filmotokio.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
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
            name = "film_person",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> crew;
}

