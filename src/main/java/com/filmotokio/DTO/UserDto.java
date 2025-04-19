package com.filmotokio.DTO;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String passwordMatch;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate birthDate;
    private String userRole;
    private String image;

    public UserDto(){}
    public UserDto(String image, String userRole, LocalDate birthDate, String passwordMatch, String password, String email, String surname, String name) {
        this.image = image;
        this.userRole = userRole;
        this.birthDate = birthDate;
        this.passwordMatch = passwordMatch;
        this.password = password;
        this.email = email;
        this.surname = surname;
        this.name = name;
    }
}
