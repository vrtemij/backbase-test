package com.backbase.movieapp.user;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.backbase.movieapp.AbstractIntegrationTest;
import com.backbase.movieapp.movies.domain.OpenMovie;
import com.backbase.movieapp.user.domain.UserMovie;
import com.backbase.movieapp.user.model.UserMovieEntity;
import com.backbase.movieapp.user.service.UserMovieService;
import com.backbase.movieapp.user.service.mapper.UserMovieMapper;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserMovieIntegrationTest extends AbstractIntegrationTest {

  private final static String OPEN_MOVIE_TITLE = "SuperMovie";
  private final static String OPEN_MOVIE_BOX_OFFICE = "$100";
  private final static int USER_ID = 1;
  private final static int USER_RATING = 10;

  @Autowired
  private UserMovieService userMovieService;

  @Autowired
  private UserMovieRepositoryTest repository;

  @Autowired
  private UserMovieMapper mapper;

  @BeforeEach
  public void beforeEach() {
    repository.deleteAll();
  }

  @Test
  @SneakyThrows
  public void givenTitleAndRating_whenMovieExists_shouldReturnSavedMovie() {
    OpenMovie openMovie = buildExistingOpenMovieForRating();

    UserMovie expectedMovie = UserMovie.builder()
        .userId(USER_ID)
        .rating(USER_RATING)
        .title(OPEN_MOVIE_TITLE)
        .boxOffice(100L)
        .build();

    UserMovie actualMovie = userMovieService.saveToLibraryWithRating(1, openMovie, 10);

    assertEquals(expectedMovie.getUserId(), actualMovie.getUserId());
    assertEquals(expectedMovie.getTitle(), actualMovie.getTitle());
    assertEquals(expectedMovie.getRating(), actualMovie.getRating());
    assertEquals(expectedMovie.getBoxOffice(), actualMovie.getBoxOffice());
  }

  @Test
  @SneakyThrows
  public void givenOpenMovie_whenMovieNotExists_shouldReturnNull() {
    UserMovie actualMovie = userMovieService.saveToLibraryWithRating(USER_ID, null, 2);
    assertNull(actualMovie);
  }

  @Test
  public void givenMovies_whenGetTopTenRatedMovies_shouldReturnSortedMovies() {
    List<UserMovieEntity> userMovieEntities = Arrays.asList(
        buildUserMovieEntityWithRatings("Film1", 1L, 10),
        buildUserMovieEntityWithRatings("Film2", 2L, 9),
        buildUserMovieEntityWithRatings("Film3", 3L, 8),
        buildUserMovieEntityWithRatings("Film4", 4L, 7),
        buildUserMovieEntityWithRatings("Film5", 5L, 6),
        buildUserMovieEntityWithRatings("Film6", 6L, 5),
        buildUserMovieEntityWithRatings("Film7", 7L, 4),
        buildUserMovieEntityWithRatings("Film8", 8L, 3),
        buildUserMovieEntityWithRatings("Film9", 9L, 2),
        buildUserMovieEntityWithRatings("Film10", 10L, 1),
        buildUserMovieEntityWithRatings("Film11", 11L, 9),
        buildUserMovieEntityWithRatings("Film12", 12L, 9)
    );
    repository.saveAll(userMovieEntities);

    userMovieEntities = userMovieEntities.stream()
        .sorted(Comparator.comparing(UserMovieEntity::getRating).reversed())
        .limit(10)
        .sorted(Comparator.comparing(UserMovieEntity::getBoxOffice).reversed())
        .collect(Collectors.toList());

    List<UserMovie> expected = mapper.toDtos(userMovieEntities);

    List<UserMovie> actual = userMovieService.getTopTenMoviesByBoxOffice(USER_ID);
    assertEquals(expected, actual);
  }

  private UserMovieEntity buildUserMovieEntityWithRatings(String title, Long boxOffice,
      Integer rating) {
    return UserMovieEntity.builder()
        .title(title)
        .userId(USER_ID)
        .boxOffice(boxOffice)
        .rating(rating)
        .build();
  }

  private OpenMovie buildExistingOpenMovieForRating() {
    return OpenMovie.builder()
        .title(OPEN_MOVIE_TITLE)
        .boxOffice(OPEN_MOVIE_BOX_OFFICE)
        .build();
  }
}
