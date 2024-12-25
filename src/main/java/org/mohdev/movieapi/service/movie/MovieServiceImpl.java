package org.mohdev.movieapi.service.movie;

import lombok.RequiredArgsConstructor;
import org.mohdev.movieapi.dtos.MovieDto;
import org.mohdev.movieapi.dtos.MoviePageResponse;
import org.mohdev.movieapi.entities.Movie;
import org.mohdev.movieapi.exceptions.MoviesNotFoundException;
import org.mohdev.movieapi.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                () -> new  MoviesNotFoundException("Movies not found")
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

    @Override
    public MoviePageResponse getAllMovieWithPaination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Movie> moviePage = movieRepository.findAll(pageable);
        List<Movie> movies = moviePage.getContent();

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

        return MoviePageResponse.builder()
                .movieDtos(movieDtos)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(moviePage.getTotalPages())
                .totalElements(moviePage.getNumberOfElements())
                .isLast(moviePage.isLast())
                .build();
    }

    @Override
    public MoviePageResponse getAllMovieWithPainationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String dir) {
        Sort sort = sortBy.equalsIgnoreCase("asc") ? Sort.by(dir)
                : Sort.by(dir).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Movie> moviePage = movieRepository.findAll(pageable);
        List<Movie> movies = moviePage.getContent();

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

        return MoviePageResponse.builder()
                .movieDtos(movieDtos)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(moviePage.getTotalPages())
                .totalElements(moviePage.getNumberOfElements())
                .isLast(moviePage.isLast())
                .build();

    }
}
