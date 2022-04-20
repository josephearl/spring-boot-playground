package uk.co.josephearl.springboot.playground;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
  @EmbeddedId
  private TagId tagId;
  @ManyToOne(optional = false)
  @MapsId("customer")
  @JoinColumn(name = "CUSTOMER")
  @Setter(AccessLevel.PACKAGE)
  @Getter(AccessLevel.NONE)
  private Customer customer;

  private Tag(TagId tagId) {
    this.tagId = tagId;
  }

  public static Tag of(String value) {
    if (value == null || value.isBlank())
      throw new IllegalArgumentException("Tag must not be null or blank");
    return new Tag(new TagId(value));
  }

  public String getValue() {
    return tagId.getTag();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tag tag = (Tag) o;
    return tagId.getTag().equals(tag.tagId.getTag());
  }

  @Override
  public int hashCode() {
    return Objects.hash(tagId.getTag());
  }
}
