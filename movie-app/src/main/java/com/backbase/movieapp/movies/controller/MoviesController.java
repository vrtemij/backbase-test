package com.backbase.movieapp.movies.controller;


import com.backbase.movieapp.movies.domain.OpenMovie;
import com.backbase.movieapp.movies.exception.MovieNotFoundException;
import com.backbase.movieapp.movies.service.MoviesAwardService;
import com.backbase.movieapp.movies.service.OpenMovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MoviesController {

  private final MoviesAwardService moviesAwardService;

  private final OpenMovieService remoteService;

  public MoviesController(MoviesAwardService moviesAwardService,
      OpenMovieService remoteService) {
    this.moviesAwardService = moviesAwardService;
    this.remoteService = remoteService;
  }

  @GetMapping("/best-picture/{name}")
  public ResponseEntity<?> wonBestPicture(@PathVariable("name") String name) {
    try {
      OpenMovie openMovie = remoteService.findMovieByName(name);
      boolean isWinner = moviesAwardService.checkIsBestPicture(openMovie.getTitle());
      return ResponseEntity.ok(isWinner);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (MovieNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
