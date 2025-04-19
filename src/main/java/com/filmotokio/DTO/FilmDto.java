package com.filmotokio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilmDto {

    private String titulo;
    private Integer ano;
    private String duracao;

    private List<Long> diretoresIds;
    private List<Long> roteiristasIds;
    private List<Long> musicosIds;
    private List<Long> atoresIds;
    private Long fotografoId;

    private String sinopse;

    private MultipartFile cartaz;
}
