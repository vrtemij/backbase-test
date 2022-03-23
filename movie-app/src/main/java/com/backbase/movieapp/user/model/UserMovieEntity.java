package com.backbase.movieapp.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tab_user_movie")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMovieEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "user_rating")
  @Min(1)
  @Max(10)
  private Integer rating;

  @Column(name = "title")
  private String title;

  @Column(name = "box_office")
  private Long  boxOffice;

}
