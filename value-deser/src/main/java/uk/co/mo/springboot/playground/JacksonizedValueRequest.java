package uk.co.mo.springboot.playground;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Jacksonized
@Builder
public class JacksonizedValueRequest {
  String title;
  @NotBlank
  String firstName;
  String lastName;
  @NotNull @Builder.Default
  LocalDate dateOfBirth = LocalDate.of(1900, 1, 1);
}
