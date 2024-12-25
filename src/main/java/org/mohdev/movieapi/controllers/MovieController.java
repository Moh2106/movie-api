package org.mohdev.movieapi.controllers;

import lombok.RequiredArgsConstructor;
import org.mohdev.movieapi.dtos.MovieDto;
import org.mohdev.movieapi.dtos.MoviePageResponse;
import org.mohdev.movieapi.service.movie.MovieService;
import org.mohdev.movieapi.utils.AppConstant;
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

    @GetMapping("/allMoviePage")
    public ResponseEntity<MoviePageResponse> allMoviePage(
            @RequestParam(defaultValue = AppConstant.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = AppConstant.PAGE_SIZE) Integer pageSize
    ){
        MoviePageResponse allMovieWithPaination = movieService.getAllMovieWithPaination(pageNumber, pageSize);
        return ResponseEntity.ok(allMovieWithPaination);
    }

    @GetMapping("/allMovieSort")
    public ResponseEntity<MoviePageResponse> allMovieSort(
            @RequestParam(defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstant.SORT_DIR, required = false) String dir
    ){
        MoviePageResponse allMovieWithPaination = movieService.getAllMovieWithPainationAndSorting(pageNumber, pageSize, sortBy, dir);
        return ResponseEntity.ok(allMovieWithPaination);
    }
}
