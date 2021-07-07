package uk.co.mo.springboot.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ValidatedController.class)
class ValidatedControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void validatedRequestBodyShouldNotBeValidated() throws Exception {
    var json = "{\"something\": \"\"}";
    var request = MockMvcRequestBuilders.put("/validated-request-body")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json);

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void validatedRequestParamShouldBeValidated() throws Exception {
    var request = MockMvcRequestBuilders.get("/validated-request-param?param=a");

    NestedServletException e = assertThrows(NestedServletException.class, () -> mockMvc.perform(request));
    assertNotNull(e.getCause());
    assertEquals(ConstraintViolationException.class, e.getCause().getClass());
  }
}
