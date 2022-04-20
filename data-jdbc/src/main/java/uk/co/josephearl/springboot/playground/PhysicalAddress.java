package uk.co.josephearl.springboot.playground;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PhysicalAddress {
  String line1;
  String line2;
  String city;
  String postCode;
  @Builder.Default
  String country = "GB";
}
