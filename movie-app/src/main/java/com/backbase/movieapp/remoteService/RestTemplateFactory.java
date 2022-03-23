package com.backbase.movieapp.remoteService;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFactory {

  private final RestResponseErrorHandler restResponseErrorHandler;
  private final RestTemplateBuilder restTemplateBuilder;

  public RestTemplateFactory(final RestResponseErrorHandler restResponseErrorHandler,
      final RestTemplateBuilder restTemplateBuilder) {
    this.restResponseErrorHandler = restResponseErrorHandler;
    this.restTemplateBuilder = restTemplateBuilder;
  }

  public RestTemplate createRestTemplate(final Integer timeoutInMillis) {
    RestTemplate restTemplate = restTemplateBuilder.setReadTimeout(Duration.ofMillis(timeoutInMillis))
        .setConnectTimeout(Duration.ofMillis(timeoutInMillis))
        .build();
    restTemplate.setErrorHandler(this.restResponseErrorHandler);
    return restTemplate;
  }
}
