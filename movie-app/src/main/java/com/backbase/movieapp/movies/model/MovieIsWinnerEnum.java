package com.backbase.movieapp.movies.model;

import lombok.Getter;

@Getter

public enum MovieIsWinnerEnum {
  YES("YES"),
  NO("NO");

  private String isWinner;

  MovieIsWinnerEnum(String isWinner) {
    this.isWinner = isWinner;
  }

}
