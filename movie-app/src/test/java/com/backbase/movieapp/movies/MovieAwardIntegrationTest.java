package com.backbase.movieapp.movies;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.backbase.movieapp.AbstractIntegrationTest;
import com.backbase.movieapp.movies.domain.MovieAwardCategory;
import com.backbase.movieapp.movies.exception.MovieNotFoundException;
import com.backbase.movieapp.movies.model.MovieAwardInformationEntity;
import com.backbase.movieapp.movies.model.MovieIsWinnerEnum;
import com.backbase.movieapp.movies.service.MoviesAwardService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieAwardIntegrationTest extends AbstractIntegrationTest {

  private final static String MOVIE_TITLE = "SuperMovie";
  private final static String MOVIE_AWARD_CATEGORY = MovieAwardCategory.BEST_PICTURE.getCategory();
  private final static MovieIsWinnerEnum MOVIE_IS_WINNER_ENUM = MovieIsWinnerEnum.YES;

  @Autowired
  private MoviesAwardService moviesAwardService;

  @Autowired
  private MovieAwardRepositoryTest repository;

  @BeforeEach
  public void beforeEach() {
    repository.deleteAll();
  }

  @Test
  @SneakyThrows
  public void givenTitle_whenMovieWonBestPicture_shouldReturnTrue() {
    MovieAwardInformationEntity savedMovie = repository.save(buildExistingMovieAward(MOVIE_IS_WINNER_ENUM));
    boolean actual = moviesAwardService.checkIsBestPicture(savedMovie.getNominee());
    assertTrue(actual);
  }

  @Test
  @SneakyThrows
  public void givenWrongTitle_whenMovieWonBestPicture_shouldThrowExcepytion() {
    assertThrows(MovieNotFoundException.class, () -> {
      moviesAwardService.checkIsBestPicture("Wrong Name");
    });
  }


  private MovieAwardInformationEntity buildExistingMovieAward(MovieIsWinnerEnum isWinner) {
    return MovieAwardInformationEntity.builder()
        .category(MOVIE_AWARD_CATEGORY)
        .nominee(MOVIE_TITLE)
        .won(isWinner.getIsWinner())
        .build();
  }
}
