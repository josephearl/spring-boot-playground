package uk.co.mo.springboot.playground;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PhysicalAddress {
  @Id
  private Long customer;
  private String line1;
  private String line2;
  private String city;
  private String postCode;
  @Builder.Default
  private String country = "GB";
}
