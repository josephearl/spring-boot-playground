package uk.co.josephearl.springboot.playground;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class SimpleValueRequest {
  @NotNull
  String fromKey;
  @NotNull
  String toKey;
}
