package uk.co.josephearl.springboot.playground;

import lombok.Value;

@Value(staticConstructor = "of")
public class Favorite {
  String thing;
  Rating rating;

  private Favorite(String thing, Rating rating) {
    if (thing == null || thing.isBlank())
      throw new IllegalArgumentException("Tag must not be null or blank");
    if (rating == null)
      throw new IllegalArgumentException("rating cannot be null");
    this.thing = thing;
    this.rating = rating;
  }
}
