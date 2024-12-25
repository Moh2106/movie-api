package org.mohdev.movieapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MoviesNotFoundException.class)
    public ProblemDetail moviesNotFound(MoviesNotFoundException exp){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exp.getMessage());
    }
}
