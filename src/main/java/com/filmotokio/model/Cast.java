package com.filmotokio.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Cast extends Person{

    @Enumerated(EnumType.STRING)
    private PersonType type;
}
