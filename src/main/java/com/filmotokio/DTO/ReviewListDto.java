package com.filmotokio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewListDto {
    private String userFullName;
    private String date;
    private String reviewText;
    private Integer rating;
}
