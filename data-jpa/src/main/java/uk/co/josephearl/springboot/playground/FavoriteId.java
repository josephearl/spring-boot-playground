package uk.co.josephearl.springboot.playground;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class FavoriteId implements Serializable {
  private Long customer;
  private Integer customerKey;

  FavoriteId(Integer customerKey) {
    this.customerKey = customerKey;
  }
}
