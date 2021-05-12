package uk.co.mo.springboot.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@TestPropertySource(properties = "logging.level.org.springframework.jdbc.core.JdbcTemplate=DEBUG")
class CustomerJdbcTemplateTest {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void shouldLoadCustomer() {
    var loadedCustomer = jdbcTemplate.queryForObject("SELECT * FROM CUSTOMER WHERE ID=?",
      (rs, rowNum) -> new Customer(
        rs.getLong("ID"),
        rs.getString("TITLE"),
        rs.getString("FIRST_NAME"),
        rs.getString("LAST_NAME"),
        LocalDate.parse(rs.getString("DATE_OF_BIRTH")),
        null,
        EmailAddress.of(rs.getString("EMAIL_ADDRESS")),
        null,
        null),
      1L);

    assertNotNull(loadedCustomer);
    assertAll(
      "loadedCustomer",
      () -> assertEquals(1L, loadedCustomer.getId()),
      () -> assertEquals("Mr", loadedCustomer.getTitle()),
      () -> assertEquals("Waylon", loadedCustomer.getFirstName()),
      () -> assertEquals("Smithers", loadedCustomer.getLastName()),
      () -> assertEquals(LocalDate.of(1954, 12, 25), loadedCustomer.getDateOfBirth()),
      () -> assertEquals(EmailAddress.of("smithers@springfieldnuclear.com"), loadedCustomer.getEmailAddress()));
  }
}
