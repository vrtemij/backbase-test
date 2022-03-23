package com.backbase.movieapp.user.service;

import com.backbase.movieapp.movies.domain.OpenMovie;
import com.backbase.movieapp.user.domain.UserMovie;
import com.backbase.movieapp.user.exception.TopTenUserMoviesNotFoundException;
import com.backbase.movieapp.user.exception.UserMovieSavingException;
import com.backbase.movieapp.user.model.UserMovieEntity;
import com.backbase.movieapp.user.repository.UserMovieRepository;
import com.backbase.movieapp.user.service.mapper.UserMovieMapper;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserMovieServiceImpl implements UserMovieService {

  private final UserMovieRepository userMovieRepository;
  private final UserMovieMapper mapper;

  public UserMovieServiceImpl(UserMovieRepository userMovieRepository, UserMovieMapper mapper) {
    this.userMovieRepository = userMovieRepository;
    this.mapper = mapper;
  }

  @Override
  @SneakyThrows
  public UserMovie saveToLibraryWithRating(Integer userId, OpenMovie dto, Integer rating) {
    try {
      UserMovie userMovie = mapper.fromOpenMovieToUserMovie(dto);
      userMovie.setRating(rating);
      userMovie.setUserId(userId);
      return mapper.toDto(userMovieRepository.save(mapper.toEntity(userMovie)));
    } catch (Exception e) {
      log.error("Error while saving to library {}", e.getMessage());
      throw new UserMovieSavingException("Error while saving to db: %s", e.getMessage());
    }
  }

  @Override
  @SneakyThrows
  public List<UserMovie> getTopTenMoviesByBoxOffice(Integer userId) {
    List<UserMovieEntity> topTenByBoxValueEntities = userMovieRepository.findTopTenBoxValueByUserId(
        userId);
    if (topTenByBoxValueEntities.isEmpty()) {
      throw new TopTenUserMoviesNotFoundException("Couldn't find top ten user rated movies");
    }
    return mapper.toDtos(topTenByBoxValueEntities);
  }

}
