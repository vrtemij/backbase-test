package com.backbase.movieapp.movies.service;

import com.backbase.movieapp.movies.domain.OpenMovie;
import com.backbase.movieapp.movies.exception.MovieNotFoundException;

public interface OpenMovieService {

  OpenMovie findMovieByName(String name)
      throws MovieNotFoundException, IllegalArgumentException;
}
