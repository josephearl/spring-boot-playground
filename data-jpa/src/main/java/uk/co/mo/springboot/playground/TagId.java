package uk.co.mo.springboot.playground;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class TagId implements Serializable {
  Long customer;
  String tag;

  TagId(String tag) {
    this.tag = tag;
  }
}
