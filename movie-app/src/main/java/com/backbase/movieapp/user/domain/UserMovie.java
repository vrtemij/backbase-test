package com.backbase.movieapp.user.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMovie {

  private Integer id;

  private Integer userId;

  @NotNull
  @Min(1)
  @Max(10)
  private Integer rating;

  @NotBlank
  private String title;

  private Long boxOffice;

}
