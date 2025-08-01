package com.filmotokio.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilmDto {

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, message = "Titulo muito curto")
    private String title;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1888, message = "Ano inválido")
    @Max(value = 2100, message = "Ano muito distante")
    private Integer year;

    @NotNull(message = "A duração é obrigatória")
    @Min(value = 1, message = "A duração deve ser maior que zero")
    private Integer duration;

    @NotEmpty(message = "Pelo menos um diretor deve ser selecionado")
    private List<Long> diretoresIds;

    @NotEmpty(message = "Pelo menos um roteirista deve ser selecionado")
    private List<Long> roteiristasIds;

    @NotEmpty(message = "Pelo menos um músico deve ser selecionado")
    private List<Long> musicosIds;

    @NotEmpty(message = "Pelo menos um ator deve ser selecionado")
    private List<Long> atoresIds;

    @NotNull(message = "Um fotógrafo deve ser selecionado")
    private Long fotografoId;

    @Size(max = 1000, message = "A sinopse deve ter no máximo 1000 caracteres")
    @NotBlank(message = "A sinopse é obrigatória")
    private String synopsis;

    @NotNull(message = "A imagem do poster é obrigatória")
    private MultipartFile poster;

    private String teaserYoutubeCode;
}

