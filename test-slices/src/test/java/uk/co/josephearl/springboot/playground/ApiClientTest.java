package uk.co.josephearl.springboot.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestClientTest(ApiClient.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@Import(JacksonConfiguration.class)
class ApiClientTest {
  @Autowired
  private ApiClient apiClient;
  @Autowired
  private MockRestServiceServer mockRestServiceServer;

  @Test
  void shouldReturnString() {
    mockRestServiceServer
      .expect(MockRestRequestMatchers.requestTo("/string"))
      .andRespond(MockRestResponseCreators.withSuccess("\"Hello StringEnum!\"", MediaType.APPLICATION_JSON));

    var value = apiClient.getValue();

    mockRestServiceServer.verify();
    assertEquals(StringEnum.STRINGY, value);
  }
}
