package com.backbase.movieapp.user.exception;

public class UserMovieSavingException extends Exception{

  public UserMovieSavingException(String message, Object... args) {
    super(String.format(message, args));
  }
}
