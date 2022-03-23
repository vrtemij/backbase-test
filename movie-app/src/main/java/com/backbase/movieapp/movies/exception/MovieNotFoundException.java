package com.backbase.movieapp.movies.exception;

public class MovieNotFoundException extends Exception {

  public MovieNotFoundException(String message, Object...args) {
    super(String.format(message, args));
  }
}
