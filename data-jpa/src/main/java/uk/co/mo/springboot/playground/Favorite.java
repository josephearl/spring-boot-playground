package uk.co.mo.springboot.playground;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = {"favoriteId", "customer"})
public class Favorite {
  @EmbeddedId
  @Getter(AccessLevel.PACKAGE)
  private FavoriteId favoriteId;
  @ManyToOne
  @MapsId("customer")
  @JoinColumn(name = "CUSTOMER")
  @Getter(AccessLevel.NONE)
  private Customer customer;
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

  void setCustomer(Customer customer, int index) {
    this.customer = customer;
    this.favoriteId = new FavoriteId(index);
  }
}
