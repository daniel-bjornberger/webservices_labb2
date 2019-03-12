package com.example.movies;


public class MovieException extends RuntimeException {

    String s;

    public MovieException(String s) {

        this.s = s;

    }

}
