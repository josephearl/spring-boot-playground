package uk.co.mo.springboot.playground;

import lombok.Value;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.data.relational.core.mapping.Column;

@Value(staticConstructor = "of")
public class EmailAddress {
  private static final EmailValidator EMAIL_VALIDATOR = new EmailValidator();
  @Column("EMAIL_ADDRESS")
  String value;

  private EmailAddress(String value) {
    if (value == null || value.isEmpty())
      throw new IllegalArgumentException("Email address must not be null or empty");
    if (!EMAIL_VALIDATOR.isValid(value, null))
      throw new IllegalArgumentException("Email address must not be invalid");
    this.value = value;
  }
}
