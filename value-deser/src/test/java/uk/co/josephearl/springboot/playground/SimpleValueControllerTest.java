package uk.co.josephearl.springboot.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(SimpleValueController.class)
class SimpleValueControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldReturnOk() throws Exception {
    var json = "{\"fromKey\": \"one\", \"toKey\": \"two\"}";
    var request = MockMvcRequestBuilders.put("/simple")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json);

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }
}
