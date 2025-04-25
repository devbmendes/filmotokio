package com.filmotokio.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Sobrenome é obrigatório")
    private String surname;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate birthDate;

    @NotBlank(message = "Password é obrigatória")
    private String password;

    @NotBlank(message = "Confirmação da password é obrigatória")
    private String passwordMatch;

    private String userType;


    public UserDto(){}

}
