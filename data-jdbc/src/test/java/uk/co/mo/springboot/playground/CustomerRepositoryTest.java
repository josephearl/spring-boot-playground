package uk.co.mo.springboot.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
      () -> assertEquals(2L, savedCustomer.getId()),
      () -> assertNotSame(newCustomer, savedCustomer));
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
