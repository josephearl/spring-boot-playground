package uk.co.mo.springboot.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

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
      () -> assertEquals("burns@springfieldnuclear.com", savedCustomer.getEmailAddress().getValue()),
      () -> {
        var physicalAddress = savedCustomer.getPhysicalAddress();
        assertNotNull(physicalAddress);
        assertAll(
          "physicalAddress",
          () -> assertEquals("1000 Mammon Lane", physicalAddress.getLine1()),
          () -> assertNull(physicalAddress.getLine2()),
          () -> assertEquals("Springfield", physicalAddress.getCity()),
          () -> assertEquals("80085", physicalAddress.getPostCode()),
          () -> assertEquals("US", physicalAddress.getCountry())
        );
      });
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
      () -> assertEquals("smithers@springfieldnuclear.com", loadedCustomer.getEmailAddress().getValue()),
      () -> {
        var physicalAddress = loadedCustomer.getPhysicalAddress();
        assertAll(
          "physicalAddress",
          () -> assertEquals("1000 Mammon Lane", physicalAddress.getLine1()),
          () -> assertNull(physicalAddress.getLine2()),
          () -> assertEquals("Springfield", physicalAddress.getCity()),
          () -> assertEquals("80085", physicalAddress.getPostCode()),
          () -> assertEquals("US", physicalAddress.getCountry())
          );
      });
  }
}
