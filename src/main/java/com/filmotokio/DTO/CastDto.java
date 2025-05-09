package com.filmotokio.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CastDto {
        @NotBlank(message = "Nome é obrigatório")
        private String name;

        @NotBlank(message = "Apelido é obrigatório")
        private String surname;

        @NotBlank(message = "E-mail é obrigatório.")
        @Email(message = "Formato de e-mail inválido.")
        private String email;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "Data de nascimento é obrigatória")
        private LocalDate birthDate;

        private String type;
}
