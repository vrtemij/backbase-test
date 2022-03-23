package com.backbase.movieapp.movies.service;

import com.backbase.movieapp.movies.domain.OpenMovie;
import com.backbase.movieapp.movies.exception.MovieNotFoundException;
import com.backbase.movieapp.remoteService.RestTemplateFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenMovieServiceImpl implements OpenMovieService {

  @Value("${movie.app.omdapi.url}")
  private String omdapiUrl;

  @Value("${movie.app.omdapi.key}")
  private String omdapiKey;

  private final RestTemplateFactory restTemplateFactory;

  public OpenMovieServiceImpl(RestTemplateFactory restTemplateFactory) {
    this.restTemplateFactory = restTemplateFactory;
  }

  @SneakyThrows
  @Override
  public OpenMovie findMovieByName(String name){
    if (name.isBlank()) {
      throw new IllegalArgumentException("Please pass the name of the film");
    }
    String url = String.format("%s%s&%s", omdapiUrl, name, omdapiKey);
    RestTemplate restTemplate = restTemplateFactory.createRestTemplate(60000);
    ResponseEntity<OpenMovie> responseEntity = restTemplate.getForEntity(url,
        OpenMovie.class);
    if (responseEntity.getStatusCodeValue() != 200 || responseEntity.getBody().getTitle() == null) {
      throw new MovieNotFoundException("Movie wasn't found with name: %s", name);
    }
    return responseEntity.getBody();
  }
}
