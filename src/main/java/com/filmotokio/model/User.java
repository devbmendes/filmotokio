package com.filmotokio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@ToString(onlyExplicitlyIncluded = true)

public class User extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private LocalDate birthDate;
    private Date registrationDate;
    private String image;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    private List<Score> scores;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    public User(String name, String surname, String email, String password, LocalDate birthDate, Date registrationDate, String image, UserRole role) {
        super(name,surname,email);
        this.password = password;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.image = image;
        this.role = role;
    }
    public User(String name,String surname, String email){
        super(name,surname,email);
    }
    public User(){
        super();
    }

}

