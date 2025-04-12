package com.filmotokio.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private Date birthDate;
    private Date registrationDate;
    private String image;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    private List<Score> scores;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}

