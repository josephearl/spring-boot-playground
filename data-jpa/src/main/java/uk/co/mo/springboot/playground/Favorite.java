package uk.co.mo.springboot.playground;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "favoriteId")
public class Favorite {
  @EmbeddedId
  @Getter(AccessLevel.PROTECTED)
  private FavoriteId favoriteId;
  private String thing;
  @Enumerated(EnumType.STRING)
  private Rating rating;

  private Favorite(String thing, Rating rating) {
    this.thing = thing;
    this.rating = rating;
  }

  public static Favorite of(String thing, Rating rating) {
    if (thing == null || thing.isBlank())
      throw new IllegalArgumentException("Tag must not be null or blank");
    if (rating == null)
      throw new IllegalArgumentException("rating cannot be null");
    return new Favorite(thing, rating);
  }
}
