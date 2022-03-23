package com.backbase.movieapp.user.exception;

public class TopTenUserMoviesNotFoundException extends Exception{

  public TopTenUserMoviesNotFoundException(String message, Object... args) {
    super(String.format(message, args));
  }
}
