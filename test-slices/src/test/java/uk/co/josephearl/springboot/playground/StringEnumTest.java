package uk.co.josephearl.springboot.playground;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
@Import(JacksonConfiguration.class)
class StringEnumTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldSerializeEnumUsingToString() throws JsonProcessingException {
    var json = objectMapper.writeValueAsString(StringEnum.STRINGY);

    assertEquals("\"Hello StringEnum!\"", json);
  }

  @Test
  void shouldDeserializeEnumUsingToString() throws JsonProcessingException {
    var json = "\"Hello StringEnum!\"";

    var stringEnum = objectMapper.readValue(json, StringEnum.class);

    assertEquals(StringEnum.STRINGY, stringEnum);
  }
}
