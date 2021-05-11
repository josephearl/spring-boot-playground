package uk.co.mo.springboot.playground;

import javax.validation.constraints.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootPlaygroundApplication {
  public static void main(@NotNull String[] args) {
    SpringApplication.run(SpringBootPlaygroundApplication.class, args);
  }
}
