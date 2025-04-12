package com.filmotokio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class User extends Person {
    private String username;
    private String password;
    private String email;
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

