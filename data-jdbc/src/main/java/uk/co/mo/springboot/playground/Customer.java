package uk.co.mo.springboot.playground;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDate;

@Value
public class Customer {
  @Id
  @With
  Long id;
  String title;
  String firstName;
  String lastName;
  LocalDate dateOfBirth;
  PhysicalAddress physicalAddress;
  @Embedded.Nullable
  EmailAddress emailAddress;

  @Builder
  Customer(String title, String firstName, String lastName, LocalDate dateOfBirth, PhysicalAddress physicalAddress, EmailAddress emailAddress) {
    this.id = null;
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.physicalAddress = physicalAddress;
    this.emailAddress = emailAddress;
  }

  @PersistenceConstructor
  Customer(Long id, String title, String firstName, String lastName, LocalDate dateOfBirth, PhysicalAddress physicalAddress, EmailAddress emailAddress) {
    this.id = id;
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.physicalAddress = physicalAddress;
    this.emailAddress = emailAddress;
  }
}
