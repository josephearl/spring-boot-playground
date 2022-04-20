package uk.co.josephearl.springboot.playground;

import lombok.*;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class EmailAddress {
  private static final EmailValidator EMAIL_VALIDATOR = new EmailValidator();
  @Column(name = "EMAIL_ADDRESS")
  private String value;

  public static EmailAddress of(String value) {
    if (value == null || value.isEmpty())
      throw new IllegalArgumentException("Email address must not be null or empty");
    if (!EMAIL_VALIDATOR.isValid(value, null))
      throw new IllegalArgumentException("Email address must not be invalid");
    return new EmailAddress(value);
  }
}
