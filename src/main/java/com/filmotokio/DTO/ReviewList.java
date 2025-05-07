package com.filmotokio.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewList {
    private String fullnameUser;
    private String filmReview;
    private Integer rating;
    private String localDate;
    private String filmTitle;
}
