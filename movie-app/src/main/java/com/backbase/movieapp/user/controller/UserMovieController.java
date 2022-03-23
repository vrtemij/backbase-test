package com.backbase.movieapp.user.controller;

import com.backbase.movieapp.auth.UserPrincipal;
import com.backbase.movieapp.movies.domain.OpenMovie;
import com.backbase.movieapp.movies.service.OpenMovieService;
import com.backbase.movieapp.user.domain.UserMovie;
import com.backbase.movieapp.user.service.UserMovieService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/movies")
public class UserMovieController {

  private final OpenMovieService remoteService;

  private final UserMovieService userMovieService;

  public UserMovieController(OpenMovieService remoteService, UserMovieService userMovieService) {
    this.remoteService = remoteService;
    this.userMovieService = userMovieService;
  }

  @PostMapping("/rating")
  public ResponseEntity<?> rateMovie(@Valid @RequestBody UserMovie userMovie) {
    OpenMovie openMovie = remoteService.findMovieByName(userMovie.getTitle());
    UserMovie savedMovie = userMovieService.saveToLibraryWithRating(
        getUserPrincipal().getUserId(), openMovie, userMovie.getRating());
    return savedMovie.getId() != null
        ? ResponseEntity.ok(savedMovie)
        : ResponseEntity.badRequest().build();
  }

  @GetMapping("/top-ten-rated")
  public ResponseEntity<List<UserMovie>> getTopTenByBoxValue() {
    List<UserMovie> response = userMovieService.getTopTenMoviesByBoxOffice(
        getUserPrincipal().getUserId());
    return response.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(response);
  }

  private UserPrincipal getUserPrincipal() {
    return (UserPrincipal) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();
  }
}
