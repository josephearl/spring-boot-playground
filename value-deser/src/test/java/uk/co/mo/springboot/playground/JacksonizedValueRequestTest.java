package uk.co.mo.springboot.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
class JacksonizedValueRequestTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldDeserializeValueUsingJacksonizedBuilder() throws IOException {
    var json = "{\"title\": \"Mr\", \"firstName\": \"Homer\", \"lastName\": \"Simpson\", \"dateOfBirth\": \"1956-05-12\"}";

    var request = objectMapper.readValue(json, JacksonizedValueRequest.class);

    assertAll(
      "request",
      () -> assertEquals("Mr", request.getTitle()),
      () -> assertEquals("Homer", request.getFirstName()),
      () -> assertEquals("Simpson", request.getLastName()),
      () -> assertEquals(LocalDate.of(1956, 5, 12), request.getDateOfBirth()));
  }

  @Test
  void shouldDeserializeValueUsingJacksonizedBuilderWithDefault() throws IOException {
    var json = "{\"title\": \"Mr\", \"firstName\": \"Homer\", \"lastName\": \"Simpson\"}";

    var request = objectMapper.readValue(json, JacksonizedValueRequest.class);

    assertAll(
      "request",
      () -> assertEquals("Mr", request.getTitle()),
      () -> assertEquals("Homer", request.getFirstName()),
      () -> assertEquals("Simpson", request.getLastName()),
      () -> assertEquals(LocalDate.of(1900, 1, 1), request.getDateOfBirth()));
  }
}
