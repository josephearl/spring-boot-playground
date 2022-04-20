package uk.co.josephearl.springboot.playground;

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
    var loadedCustomer = jdbcTemplate.queryForObject("SELECT C.*, P.* FROM CUSTOMER C LEFT JOIN PHYSICAL_ADDRESS P ON P.CUSTOMER=C.ID WHERE C.ID=?",
      (rs, rowNum) -> new Customer(
        rs.getLong("ID"),
        rs.getString("TITLE"),
        rs.getString("FIRST_NAME"),
        rs.getString("LAST_NAME"),
        LocalDate.parse(rs.getString("DATE_OF_BIRTH")),
        PhysicalAddress.builder()
          .line1(rs.getString("LINE1"))
          .line2(rs.getString("LINE2"))
          .city(rs.getString("CITY"))
          .postCode(rs.getString("POST_CODE"))
          .country(rs.getString("COUNTRY"))
          .build(),
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
      () -> assertEquals(EmailAddress.of("smithers@springfieldnuclear.com"), loadedCustomer.getEmailAddress()),
      () -> assertEquals(PhysicalAddress.builder()
        .line1("1000 Mammon Lane")
        .line2(null)
        .city("Springfield")
        .postCode("80085")
        .country("US")
        .build(), loadedCustomer.getPhysicalAddress()));
  }
}
