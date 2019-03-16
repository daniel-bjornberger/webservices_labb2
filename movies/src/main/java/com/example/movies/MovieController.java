package com.example.movies;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class MovieController {

    private MovieRepository repository;


    public MovieController(MovieRepository repository) {

        this.repository = repository;

    }


    @GetMapping("/movies")
    public List<Movie> getAllMovies(){

        return repository.findAll();

    }


    @GetMapping("/movies/{movieId}")
    public Movie getOneMovie(@PathVariable String movieId){

        return repository.findById(movieId)

                .orElseThrow( () -> new MovieNotFoundException(movieId));

    }


    @PostMapping("/movies")
    public Movie createMovie(@RequestBody Movie movie) {

        if (repository.existsById(movie.getMovieId())) {

            throw new MovieAlreadyExistsException(movie.getMovieId());

        }

        else {

            return repository.save(movie);

        }

    }


    @DeleteMapping("/movies/{movieId}")
    public void deleteMovie(@PathVariable String movieId){

        if (repository.existsById(movieId)) {

            repository.deleteById(movieId);

        }

        else {

            throw new MovieNotFoundException(movieId);

        }

    }


    @PutMapping("/movies/{movieId}")
    public Movie changeMovie(@RequestBody Movie movie, @PathVariable String movieId){

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

        }).orElseThrow( () -> new MovieNotFoundException(movieId));

    }


}
