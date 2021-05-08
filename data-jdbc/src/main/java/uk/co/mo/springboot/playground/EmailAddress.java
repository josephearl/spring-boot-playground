package uk.co.mo.springboot.playground;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.ValidationException;

@Value
// Override constructor access level
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailAddress {
  private static final EmailValidator EMAIL_VALIDATOR = new EmailValidator();
  @Column("email_address")
  String value;

  public static EmailAddress of(String emailAddress) throws ValidationException {
    if (!EMAIL_VALIDATOR.isValid(emailAddress, null))
      throw new ValidationException("Invalid email address");
    return new EmailAddress(emailAddress);
  }
}
