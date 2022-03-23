package com.backbase.movieapp.movies.domain;

import lombok.Getter;

//TODO: add all categories
@Getter
public enum MovieAwardCategory {
  BEST_PICTURE("Best Picture");

  private String category;

  MovieAwardCategory(String category) {
    this.category = category;
  }

}
