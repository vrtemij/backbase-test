package com.backbase.movieapp.user.repository;

import com.backbase.movieapp.user.model.UserMovieEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovieEntity, Integer> {

  @Query(nativeQuery = true, value = "select * from (select * from tab_user_movie e where e.user_id = :id order by e.user_rating desc limit 10) as rated order by rated.box_office desc")
  List<UserMovieEntity> findTopTenBoxValueByUserId(@Param("id") Integer userId);
}
