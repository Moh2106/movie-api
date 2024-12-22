package org.mohdev.movieapi.controllers;

import lombok.RequiredArgsConstructor;
import org.mohdev.movieapi.dtos.MovieDto;
import org.mohdev.movieapi.service.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto){
        MovieDto movieDto1 = movieService.addMovie(movieDto);
        return ResponseEntity.ok(movieDto1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Integer id){
        MovieDto movie = movieService.getMovie(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovie(){
        return ResponseEntity.ok(movieService.getAllMovie());
    }
}
