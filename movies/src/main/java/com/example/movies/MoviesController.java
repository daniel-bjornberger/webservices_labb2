package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class MoviesController {

    private MovieRepository repository;


    public MoviesController(MovieRepository repository) {

        this.repository = repository;

    }


    @GetMapping("/movies")
    public List<Movie> getAll(){

        return repository.findAll();

    }


    @GetMapping("/movies/{movieId}")
    public Movie getOne(@PathVariable String movieId){

        return repository.findById(movieId)

                .orElseThrow( () -> new MovieException("No movie found with id: " + movieId));

    }


    @PostMapping("/movies")
    public Movie create(@RequestBody Movie movie) {

        return repository.save(movie);

    }


    @DeleteMapping("/movies/{movieId}")
    public void delete(@PathVariable String movieId){

        repository.deleteById(movieId);

    }


    @PutMapping("/movies/{movieId}")
    public Movie change(@RequestBody Movie movie, @PathVariable String movieId){

        return repository.findById(movieId).map(storedMovie -> {

            storedMovie.setHaveSeen(movie.isHaveSeen());
            storedMovie.setWantToSee(movie.isWantToSee());
            storedMovie.setTitle(movie.getTitle());
            storedMovie.setDirector(movie.getDirector());
            storedMovie.setActors(movie.getActors());
            storedMovie.setRunTime(movie.getRunTime());
            storedMovie.setGenre(movie.getGenre());
            storedMovie.setReleaseDate(movie.getReleaseDate());
            storedMovie.setCountry(movie.getCountry());
            storedMovie.setImdbRating(movie.getImdbRating());

            return repository.save(storedMovie);

        }).orElseThrow( () -> new MovieException("No movie found with id: " + movieId));

    }


}
