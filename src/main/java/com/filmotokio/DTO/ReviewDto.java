package com.filmotokio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long filmId;
    private Long userId;
    private String filmReview;
    private Integer rating;
}
