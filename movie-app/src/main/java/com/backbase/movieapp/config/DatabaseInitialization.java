package com.backbase.movieapp.config;

import com.backbase.movieapp.awardParser.MovieAwardParser;
import com.backbase.movieapp.movies.model.MovieAwardInformationEntity;
import com.backbase.movieapp.movies.repository.MoviesAwardsInformationRepository;
import com.backbase.movieapp.user.model.UserEntity;
import com.backbase.movieapp.user.model.UserRole;
import com.backbase.movieapp.user.repository.UserRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DatabaseInitialization {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final MovieAwardParser movieAwardParser;

  private final MoviesAwardsInformationRepository moviesAwardsInformationRepository;

  public DatabaseInitialization(UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      MovieAwardParser movieAwardParser,
      MoviesAwardsInformationRepository moviesAwardsInformationRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.movieAwardParser = movieAwardParser;
    this.moviesAwardsInformationRepository = moviesAwardsInformationRepository;
  }

  @PostConstruct
  public void init() {
    initializeUsers();
    loadAwardsFromCsv();
  }

  private void initializeUsers() {
    try {
      this.userRepository.deleteAll();
      UserEntity user =  UserEntity.builder()
          .id(10169)
          .username("User")
          .password(passwordEncoder.encode("password"))
          .role(UserRole.USER)
          .build();
      List<UserEntity> users = List.of(user);
      this.userRepository.saveAll(users);
    } catch (Exception e) {
      log.error("User creation failed {}", e.getMessage());
      throw new RuntimeException("Failed to start application, exception while creating users");
    }
  }

  private void loadAwardsFromCsv() {
    try {
      if (moviesAwardsInformationRepository.count() == 0) {
        List<MovieAwardInformationEntity> movieAwardInformationEntities = movieAwardParser.parseCsv();
        moviesAwardsInformationRepository.saveAll(movieAwardInformationEntities);
      }
    } catch (Exception e) {
      log.error("Incorrect csv file: {}", e.getMessage());
      throw new RuntimeException("Failed to start application, please provide correct csv");
    }
  }
}