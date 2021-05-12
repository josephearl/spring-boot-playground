package uk.co.mo.springboot.playground;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

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
  @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "customer")
  private PhysicalAddress physicalAddress;
  @Embedded
  private EmailAddress emailAddress;
  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "customer")
  private Set<Tag> tags;
  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "customer")
  @OrderBy("favoriteId.customerKey")
  private List<Favorite> favorites;

  @Builder
  Customer(String title, String firstName, String lastName, LocalDate dateOfBirth, PhysicalAddress physicalAddress, EmailAddress emailAddress, @Singular Set<Tag> tags, @Singular List<Favorite> favorites) {
    this.id = null;
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.physicalAddress = physicalAddress;
    if (this.physicalAddress != null) {
      this.physicalAddress.setCustomer(this);
    }
    this.emailAddress = emailAddress;
    this.tags = tags != null ? tags : Collections.emptySet();
    this.tags.forEach(tag -> tag.setCustomer(this));
    this.favorites = favorites != null ? favorites : Collections.emptyList();
    IntStream.range(0, this.favorites.size()).forEach(i -> this.favorites.get(i).setCustomer(this, i));
  }
}
