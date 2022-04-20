package uk.co.josephearl.springboot.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(JacksonizedValueController.class)
class JacksonizedValueControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldReturnOk() throws Exception {
    var json = "{\"title\": \"Mr\", \"firstName\": \"Homer\", \"lastName\": \"Simpson\", \"dateOfBirth\": \"1956-05-12\"}";
    var request = MockMvcRequestBuilders.put("/jacksonized")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json);

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void shouldReturnOkWithoutDateOfBirth() throws Exception {
    var json = "{\"title\": \"Mr\", \"firstName\": \"Homer\", \"lastName\": \"Simpson\"}";
    var request = MockMvcRequestBuilders.put("/jacksonized")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json);

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }
}
