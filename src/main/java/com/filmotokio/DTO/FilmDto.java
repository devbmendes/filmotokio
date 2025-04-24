package com.filmotokio.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilmDto {

    private String title;
    private Integer year;
    private Integer duration;

    private List<Long> diretoresIds;
    private List<Long> roteiristasIds;
    private List<Long> musicosIds;
    private List<Long> atoresIds;
    private Long fotografoId;

    @Size(max = 1000)
    @NotBlank
    private String synopsis;

    private MultipartFile poster;
}
