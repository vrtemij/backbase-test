package com.backbase.movieapp.user.repository;

import com.backbase.movieapp.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  UserEntity findByUsername(String username);
}