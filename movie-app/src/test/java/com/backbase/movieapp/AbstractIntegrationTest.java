package com.backbase.movieapp;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieAppApplication.class)
@ContextConfiguration(
    initializers = AbstractIntegrationTest.ContainerInitializer.class,
    classes = MovieAppConfigurationSupport.class
)
public abstract class AbstractIntegrationTest {

  @ClassRule
  public static final MySQLContainer<?> container;

  static {
    container = new MySQLContainer<>("mysql:8.0.28")
        .withUsername("root")
        .withPassword("password");

    container.start();
  }

  static class ContainerInitializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
      TestPropertyValues.of("spring.datasource.url=" + container.getJdbcUrl(),
              "spring.datasource.username=" + container.getUsername(),
              "spring.datasource.password=" + container.getPassword())
          .applyTo(applicationContext.getEnvironment());
    }
  }

}
