package uk.co.mo.springboot.playground;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = "customer")
public class PhysicalAddress {
  @Id
  @Getter(AccessLevel.NONE)
  private Long customer;
  private String line1;
  private String line2;
  private String city;
  private String postCode;
  @Builder.Default
  private String country = "GB";
}
