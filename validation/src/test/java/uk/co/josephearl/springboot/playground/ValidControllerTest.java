package uk.co.josephearl.springboot.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ValidController.class)
class ValidControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void validRequestBodyShouldBeValidated() throws Exception {
    var json = "{\"something\": \"\"}";
    var request = MockMvcRequestBuilders.put("/valid-request-body")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json);

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  void validRequestParamShouldNotBeValidated() throws Exception {
    var request = MockMvcRequestBuilders.get("/valid-request-param?param=a");

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }
}
