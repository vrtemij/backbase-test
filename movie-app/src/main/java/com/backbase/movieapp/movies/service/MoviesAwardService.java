package com.backbase.movieapp.movies.service;

import com.backbase.movieapp.movies.exception.MovieNotFoundException;

public interface MoviesAwardService {

  boolean checkIsBestPicture(String name) throws MovieNotFoundException;

}
