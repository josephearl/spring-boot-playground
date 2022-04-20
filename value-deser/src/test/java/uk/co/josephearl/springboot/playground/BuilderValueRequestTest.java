package uk.co.josephearl.springboot.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class BuilderValueRequestTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldDeserializeValueUsingConstructor() throws IOException {
    var json = "{\"title\": \"Mr\", \"firstName\": \"Homer\", \"lastName\": \"Simpson\", \"dateOfBirth\": \"1956-05-12\"}";

    var request = objectMapper.readValue(json, BuilderValueRequest.class);

    assertAll(
      "request",
      () -> assertEquals("Mr", request.getTitle()),
      () -> assertEquals("Homer", request.getFirstName()),
      () -> assertEquals("Simpson", request.getLastName()),
      () -> assertEquals(LocalDate.of(1956, 5, 12), request.getDateOfBirth()));
  }

  @Test
  void shouldDeserializeValueUsingUsingConstructorWithoutDefault() throws IOException {
    var json = "{\"title\": \"Mr\", \"firstName\": \"Homer\", \"lastName\": \"Simpson\"}";

    var request = objectMapper.readValue(json, BuilderValueRequest.class);

    assertAll(
      "request",
      () -> assertEquals("Mr", request.getTitle()),
      () -> assertEquals("Homer", request.getFirstName()),
      () -> assertEquals("Simpson", request.getLastName()),
      () -> assertNull(request.getDateOfBirth()));
  }
}
