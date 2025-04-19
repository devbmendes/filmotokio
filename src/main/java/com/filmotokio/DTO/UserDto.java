package com.filmotokio.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserDto {
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Apelido é obrigatório")
    private String surname;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String password;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Nao corresponde a password anterior")
    private String passwordMatch;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento é obrigatória")
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
