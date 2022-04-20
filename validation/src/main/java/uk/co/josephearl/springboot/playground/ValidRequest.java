package uk.co.josephearl.springboot.playground;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class ValidRequest {
  @NotBlank
  String something;

  @JsonCreator
  public ValidRequest(String something) {
    this.something = something;
  }
}
