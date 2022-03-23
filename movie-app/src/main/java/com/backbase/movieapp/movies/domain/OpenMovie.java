package com.backbase.movieapp.movies.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenMovie {

  @JsonProperty("Title")
  private String title;

  @JsonProperty("BoxOffice")
  private String boxOffice;

  @JsonProperty("Director")
  private String director;

  @JsonProperty("Year")
  private Integer year;

  @JsonProperty("Country")
  private String country;

  private String imdbID;
}
