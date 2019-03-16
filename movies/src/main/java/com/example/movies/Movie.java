package com.example.movies;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
class Movie {

    private @Id String movieId;
    private boolean haveSeen;
    private boolean wantToSee;
    private String title;
    private String director;
    private String actors;
    private String runTime;
    private String genre;
    private String releaseDate;
    private String country;
    private String imdbRating;

}