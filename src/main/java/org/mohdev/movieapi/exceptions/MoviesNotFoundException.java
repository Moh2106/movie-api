package org.mohdev.movieapi.exceptions;

public class MoviesNotFoundException extends RuntimeException{
    public MoviesNotFoundException(String message){
        super(message);
    }
}
