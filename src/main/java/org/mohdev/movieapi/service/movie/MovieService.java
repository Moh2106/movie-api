package org.mohdev.movieapi.service.movie;

import org.mohdev.movieapi.dtos.MovieDto;

import java.util.List;

public interface MovieService {
    MovieDto addMovie(MovieDto movieDto);
    MovieDto getMovie(Integer id);
    List<MovieDto> getAllMovie();
}
