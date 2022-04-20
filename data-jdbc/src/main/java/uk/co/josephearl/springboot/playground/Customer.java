package uk.co.josephearl.springboot.playground;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
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
  List<Favorite> favorites;

  @Builder
  Customer(String title, String firstName, String lastName, LocalDate dateOfBirth, PhysicalAddress physicalAddress, EmailAddress emailAddress, @Singular Set<Tag> tags, @Singular List<Favorite> favorites) {
    this(null, title, firstName, lastName, dateOfBirth, physicalAddress, emailAddress, tags, favorites);
  }

  @PersistenceConstructor
  Customer(Long id, String title, String firstName, String lastName, LocalDate dateOfBirth, PhysicalAddress physicalAddress, EmailAddress emailAddress, Set<Tag> tags, List<Favorite> favorites) {
    this.id = id;
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.physicalAddress = physicalAddress;
    this.emailAddress = emailAddress;
    this.tags = tags != null ? tags : Collections.emptySet();
    this.favorites = favorites != null ? favorites : Collections.emptyList();
  }
}
