package uk.co.mo.springboot.playground;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"customerId", "customer"})
public class PhysicalAddress {
  @Id
  @Column(name = "CUSTOMER")
  @Getter(AccessLevel.PACKAGE)
  private Long customerId;
  @OneToOne
  @MapsId
  @JoinColumn(name = "CUSTOMER")
  @Setter(AccessLevel.PACKAGE)
  @Getter(AccessLevel.NONE)
  private Customer customer;
  private String line1;
  private String line2;
  private String city;
  private String postCode;
  @Builder.Default
  private String country = "GB";
}
