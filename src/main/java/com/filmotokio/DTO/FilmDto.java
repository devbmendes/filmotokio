package com.filmotokio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilmDto {
    private String title;
    private int year;
    private int duration;
    private String synopsis;
    private String poster;
}
