package org.mohdev.movieapi.service.movie;

import org.mohdev.movieapi.dtos.MovieDto;
import org.mohdev.movieapi.dtos.MoviePageResponse;

import java.util.List;

public interface MovieService {
    MovieDto addMovie(MovieDto movieDto);
    MovieDto getMovie(Integer id);
    List<MovieDto> getAllMovie();
    MoviePageResponse getAllMovieWithPaination(Integer pageNumber, Integer pageSize);
    MoviePageResponse getAllMovieWithPainationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String dir);

}



