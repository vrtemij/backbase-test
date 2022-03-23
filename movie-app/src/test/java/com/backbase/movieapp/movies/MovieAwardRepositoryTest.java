package com.backbase.movieapp.movies;

import com.backbase.movieapp.movies.model.MovieAwardInformationEntity;
import com.backbase.movieapp.movies.model.MovieIsWinnerEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieAwardRepositoryTest extends JpaRepository<MovieAwardInformationEntity, Integer> {
  MovieAwardInformationEntity findFirstByCategoryAndWon(String category, MovieIsWinnerEnum isWinnerEnum);
}
