package uk.co.mo.springboot.playground;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class BuilderValueRequest {
  String title;
  @NotBlank
  String firstName;
  String lastName;
  @NotNull @Builder.Default
  LocalDate dateOfBirth = LocalDate.of(1900, 1, 1);
}
