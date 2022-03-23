package com.backbase.movieapp.user.service;

import com.backbase.movieapp.movies.domain.OpenMovie;
import com.backbase.movieapp.user.domain.UserMovie;
import java.util.List;

public interface UserMovieService {

  UserMovie saveToLibraryWithRating(Integer userId, OpenMovie dto, Integer rating);

  List<UserMovie> getTopTenMoviesByBoxOffice(Integer userId);
}
