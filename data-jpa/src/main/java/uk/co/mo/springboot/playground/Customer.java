package uk.co.mo.springboot.playground;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  @OneToOne(orphanRemoval = true)
  @PrimaryKeyJoinColumn
  private PhysicalAddress physicalAddress;
  @Embedded
  private EmailAddress emailAddress;
  @OneToMany(orphanRemoval = true)
  @JoinColumn(name = "CUSTOMER")
  private Set<Tag> tags;
  @OneToMany(orphanRemoval = true)
  @JoinColumn(name = "CUSTOMER")
  private List<Favorite> favorites;

  @Builder
  Customer(String title, String firstName, String lastName, LocalDate dateOfBirth, PhysicalAddress physicalAddress, EmailAddress emailAddress, @Singular Set<Tag> tags, @Singular List<Favorite> favorites) {
    this.id = null;
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
