package uk.co.josephearl.springboot.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
class SimpleValueRequestTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldDeserializeValueUsingConstructor() throws IOException {
    String json = "{\"fromKey\": \"one\", \"toKey\": \"two\"}";

    var request = objectMapper.readValue(json, SimpleValueRequest.class);

    assertAll(
      "request",
      () -> assertEquals("one", request.getFromKey()),
      () -> assertEquals("two", request.getToKey()));
  }
}
