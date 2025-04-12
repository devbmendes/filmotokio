package com.filmotokio.model;

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
@ToString
@NoArgsConstructor
public class User extends Person {
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

    public User(String surname, String name,String email, String password, Date birthDate, Date registrationDate, String image, UserRole role) {
        super(name,surname);
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.image = image;
        this.role = role;
    }
}

