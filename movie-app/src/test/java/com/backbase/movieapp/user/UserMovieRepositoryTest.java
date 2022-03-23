package com.backbase.movieapp.user;

import com.backbase.movieapp.user.model.UserMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMovieRepositoryTest extends JpaRepository<UserMovieEntity, Integer> {

}
