package com.example.movies;


class MovieAlreadyExistsException extends RuntimeException {


    MovieAlreadyExistsException(String movieId) {

        super("A movie with id " + movieId + "already exists in the database.");

    }

}
