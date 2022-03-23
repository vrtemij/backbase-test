package com.backbase.movieapp.movies.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tab_awards_information")
public class MovieAwardInformationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "year")
  private String year;

  @Column(name = "nominee", columnDefinition = "text")
  private String nominee;

  @Column(name = "category")
  private String category;

  @Column(name = "additional_info", columnDefinition = "text")
  private String additionalInfo;

  //TODO: enum
  @Column(name = "won")
  private String won;


}
