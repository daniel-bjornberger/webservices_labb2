package com.example.movies;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Movie {

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

    /*private final static String url = "http://www.omdbapi.com/?apikey=43576692&i=";
    private final static RestTemplate restTemplate = new RestTemplate();*/


    /*public Movie(String movieId, boolean haveSeen, boolean wantToSee) throws JSONException {

        JSONObject obj = new JSONObject(restTemplate.getForObject(url + movieId, String.class));

        if (obj.getBoolean("Response")) {

            this.movieId = movieId;
            this.haveSeen = haveSeen;
            this.wantToSee = wantToSee;

            this.title = obj.getString("Title");
            this.director = obj.getString("Director");
            this.actors = obj.getString("Actors");
            this.runTime = obj.getString("Runtime");
            this.genre = obj.getString("Genre");
            this.releaseDate = obj.getString("Released");
            this.country = obj.getString("Country");
            this.imdbRating = obj.getString("imdbRating");

        }

        else {

            throw new MovieException("No movie found with id: " + movieId);

        }

    }*/


}