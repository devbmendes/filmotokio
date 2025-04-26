package com.filmotokio.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Cast extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PersonType type;



    public Cast(String name, String surname,String email, PersonType type) {
        super(name, surname,email);
        this.type = type;
    }
}
