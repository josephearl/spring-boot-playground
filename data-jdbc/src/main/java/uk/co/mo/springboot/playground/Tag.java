package uk.co.mo.springboot.playground;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Column;

@Value(staticConstructor = "of")
public class Tag {
  @Column("tag")
  String value;

  private Tag(String value) {
    if (value == null || value.isBlank())
      throw new IllegalArgumentException("Tag must not be null or blank");
    this.value = value;
  }
}
