package com.filmotokio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer year;
    private Integer duration;
    @Lob
    private String synopsis;
    private String poster;

    @OneToMany(mappedBy = "film")
    @JsonIgnore
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

    public Film(String title, Integer year, Integer duration, String synopsis, String poster, boolean migrate) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.poster = poster;
    }
}

