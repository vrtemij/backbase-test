package com.backbase.movieapp.movies.repository;

import com.backbase.movieapp.movies.model.MovieAwardInformationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesAwardsInformationRepository extends JpaRepository<MovieAwardInformationEntity, Integer> {
  Optional<MovieAwardInformationEntity> findByNomineeAndCategory(String nominee, String category);
}
