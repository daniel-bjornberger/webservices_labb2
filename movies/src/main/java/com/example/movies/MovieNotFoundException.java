package com.example.movies;


class MovieNotFoundException extends RuntimeException {


    MovieNotFoundException(String movieId) {

        super("No movie found with id " + movieId + ".");

    }

}
