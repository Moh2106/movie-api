package org.mohdev.movieapi.service.movie;

import lombok.RequiredArgsConstructor;
import org.mohdev.movieapi.dtos.MovieDto;
import org.mohdev.movieapi.entities.Movie;
import org.mohdev.movieapi.repositories.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    @Override
    public MovieDto addMovie(MovieDto movieDto) {
        // Map dto to movie
        Movie movie = Movie.builder()
                .title(movieDto.getTitle())
                .director(movieDto.getDirector())
                .studio(movieDto.getStudio())
                .movieCast(movieDto.getMovieCast())
                .releaseYear(movieDto.getReleaseYear())
                .build();

        // Save movie object
        Movie save = movieRepository.save(movie);

        // Map movie to moviedto
        return MovieDto.builder()
                .id(save.getId())
                .title(save.getTitle())
                .director(save.getDirector())
                .studio(movie.getStudio())
                .releaseYear(movie.getReleaseYear())
                .movieCast(movie.getMovieCast())
                .build();
    }

    @Override
    public MovieDto getMovie(Integer id) {
        Movie byId = movieRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Movie doesn't exist")
        );

        // Map Movie to Movie dto
        return MovieDto.builder()
                .id(byId.getId())
                .title(byId.getTitle())
                .director(byId.getDirector())
                .studio(byId.getStudio())
                .releaseYear(byId.getReleaseYear())
                .movieCast(byId.getMovieCast())
                .build();
    }

    @Override
    public List<MovieDto> getAllMovie() {
        // 1- fetch all data from DB
        List<Movie> movies = movieRepository.findAll();

        List<MovieDto> movieDtos = new ArrayList<>();

        // 2- Iterate through the list
        for (Movie movie: movies
             ) {
            MovieDto build = MovieDto.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .director(movie.getDirector())
                    .studio(movie.getStudio())
                    .releaseYear(movie.getReleaseYear())
                    .movieCast(movie.getMovieCast())
                    .build();

            movieDtos.add(build);
        }
        return movieDtos;
    }
}
