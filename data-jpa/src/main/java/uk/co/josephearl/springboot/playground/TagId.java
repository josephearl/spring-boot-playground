package uk.co.josephearl.springboot.playground;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class TagId implements Serializable {
  private Long customer;
  private String tag;

  TagId(String tag) {
    this.tag = tag;
  }
}
