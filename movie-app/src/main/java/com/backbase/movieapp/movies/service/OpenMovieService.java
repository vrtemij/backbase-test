package com.backbase.movieapp.movies.service;

import com.backbase.movieapp.movies.domain.OpenMovie;

public interface OpenMovieService {

  OpenMovie findMovieByName(String name);
}
