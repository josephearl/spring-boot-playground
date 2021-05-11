package uk.co.mo.springboot.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=validate")
class CustomerRepositoryTest {
  @Autowired
  private CustomerRepository customerRepository;

  @Test
  void shouldGenerateNewIdWhenCreatingNewCustomer() {
    var newCustomer = Customer.builder()
      .title("Mr")
      .firstName("Monty")
      .lastName("Burns")
      .dateOfBirth(LocalDate.of(1886, 9, 15))
      .emailAddress(EmailAddress.of("burns@springfieldnuclear.com"))
      .physicalAddress(
        PhysicalAddress.builder()
          .line1("1000 Mammon Lane")
          .city("Springfield")
          .postCode("80085")
          .country("US")
          .build())
      .tag(Tag.of("creepy"))
      .tag(Tag.of("moneybags"))
      .favorite(Favorite.of("Mr Burns", Rating.AWESOME))
      .favorite(Favorite.of("Mr Smithers", Rating.MEH))
      .build();

    var savedCustomer = customerRepository.save(newCustomer);

    assertAll(
      "savedCustomer",
      () -> assertSame(newCustomer, savedCustomer),
      () -> assertEquals(2L, savedCustomer.getId()),
      () -> assertEquals("Mr", savedCustomer.getTitle()),
      () -> assertEquals("Monty", savedCustomer.getFirstName()),
      () -> assertEquals("Burns", savedCustomer.getLastName()),
      () -> assertEquals(LocalDate.of(1886, 9, 15), savedCustomer.getDateOfBirth()),
      () -> assertEquals(EmailAddress.of("burns@springfieldnuclear.com"), savedCustomer.getEmailAddress()),
      () -> assertEquals(PhysicalAddress.builder()
        .line1("1000 Mammon Lane")
        .line2(null)
        .city("Springfield")
        .postCode("80085")
        .country("US")
        .build(), savedCustomer.getPhysicalAddress()),
//      () -> assertEquals(2L, savedCustomer.getPhysicalAddress().getCustomer()),
      () -> assertEquals(Set.of(Tag.of("creepy"), Tag.of("moneybags")), savedCustomer.getTags()),
//      () -> assertTrue(savedCustomer.getTags().stream().allMatch(t -> t.getTagId().getCustomer().equals(2L))),
      () -> assertEquals(List.of(Favorite.of("Mr Burns", Rating.AWESOME),
        Favorite.of("Mr Smithers", Rating.MEH)), savedCustomer.getFavorites())
//      ,() -> assertTrue(savedCustomer.getFavorites().stream().allMatch(t -> t.getFavoriteId().getCustomer().equals(2L)))
    );
  }

  @Test
  void shouldLoadCustomer() {
    var foundCustomer = customerRepository.findById(1L);
    assertTrue(foundCustomer.isPresent());

    var loadedCustomer = foundCustomer.get();
    assertAll(
      "loadedCustomer",
      () -> assertEquals(1L, loadedCustomer.getId()),
      () -> assertEquals("Mr", loadedCustomer.getTitle()),
      () -> assertEquals("Waylon", loadedCustomer.getFirstName()),
      () -> assertEquals("Smithers", loadedCustomer.getLastName()),
      () -> assertEquals(LocalDate.of(1954, 12, 25), loadedCustomer.getDateOfBirth()),
      () -> assertEquals(EmailAddress.of("smithers@springfieldnuclear.com"), loadedCustomer.getEmailAddress()),
      () -> assertEquals(PhysicalAddress.builder()
        .line1("1000 Mammon Lane")
        .line2(null)
        .city("Springfield")
        .postCode("80085")
        .country("US")
        .build(), loadedCustomer.getPhysicalAddress()),
      () -> assertEquals(1L, loadedCustomer.getPhysicalAddress().getCustomer()),
      () -> assertEquals(Set.of(Tag.of("soppy")), loadedCustomer.getTags()),
      () -> assertTrue(loadedCustomer.getTags().stream().allMatch(t -> t.getTagId().getCustomer().equals(1L))),
      () -> assertEquals(List.of(Favorite.of("Ice cream", Rating.AWESOME),
        Favorite.of("Mr Burns", Rating.SUCKS)), loadedCustomer.getFavorites()),
      () -> assertTrue(loadedCustomer.getFavorites().stream().allMatch(t -> t.getFavoriteId().getCustomer().equals(1L))));
  }
}
