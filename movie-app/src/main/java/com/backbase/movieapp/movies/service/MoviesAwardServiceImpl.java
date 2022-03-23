package com.backbase.movieapp.movies.service;

import com.backbase.movieapp.movies.domain.MovieAwardCategory;
import com.backbase.movieapp.movies.exception.MovieNotFoundException;
import com.backbase.movieapp.movies.model.MovieAwardInformationEntity;
import com.backbase.movieapp.movies.repository.MoviesAwardsInformationRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoviesAwardServiceImpl implements MoviesAwardService {

  private final MoviesAwardsInformationRepository moviesAwardsInformationRepository;

  public MoviesAwardServiceImpl(MoviesAwardsInformationRepository moviesAwardsInformationRepository) {
    this.moviesAwardsInformationRepository = moviesAwardsInformationRepository;
  }

  @Override
  @SneakyThrows
  public boolean checkIsBestPicture(String name) {
    MovieAwardInformationEntity entity =
        moviesAwardsInformationRepository.findByNomineeAndCategory(name,
                MovieAwardCategory.BEST_PICTURE.getCategory())
        .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
    return "YES".equals(entity.getWon());
  }
}
