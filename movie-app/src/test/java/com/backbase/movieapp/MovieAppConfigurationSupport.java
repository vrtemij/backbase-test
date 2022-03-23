package com.backbase.movieapp;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.TestPropertySource;

@TestConfiguration
@TestPropertySource(locations = "/application-docker.properties")
public class MovieAppConfigurationSupport {

}
