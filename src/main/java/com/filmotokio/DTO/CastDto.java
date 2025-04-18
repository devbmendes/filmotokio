package com.filmotokio.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CastDto {
    private String name;
    private String surname;
    private String type;
}
