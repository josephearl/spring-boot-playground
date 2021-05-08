package uk.co.mo.springboot.playground;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Value
public class Customer {
  @Id
  Long id;
  String title;
  String firstName;
  String lastName;
  LocalDate dateOfBirth;
  PhysicalAddress physicalAddress;
  @Embedded.Nullable
  EmailAddress emailAddress;
  Set<Tag> tags;

  @Builder
  Customer(String title, String firstName, String lastName, LocalDate dateOfBirth, PhysicalAddress physicalAddress, EmailAddress emailAddress, @Singular Set<Tag> tags) {
    this.id = null;
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.physicalAddress = physicalAddress;
    this.emailAddress = emailAddress;
    this.tags = tags != null ? tags : Collections.emptySet();
  }

  @PersistenceConstructor
  Customer(Long id, String title, String firstName, String lastName, LocalDate dateOfBirth, PhysicalAddress physicalAddress, EmailAddress emailAddress, Set<Tag> tags) {
    this.id = id;
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.physicalAddress = physicalAddress;
    this.emailAddress = emailAddress;
    this.tags = tags != null ? tags : Collections.emptySet();
  }
}
