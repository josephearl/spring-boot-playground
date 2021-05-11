package uk.co.mo.springboot.playground;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tag {
  @EmbeddedId
  private TagId tagId;

  public static Tag of(String value) {
    if (value == null || value.isBlank())
      throw new IllegalArgumentException("Tag must not be null or blank");
    return new Tag(new TagId(value));
  }

  public String getValue() {
    return tagId.tag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tag tag = (Tag) o;
    return tagId.tag.equals(tag.tagId.tag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tagId.tag);
  }
}
