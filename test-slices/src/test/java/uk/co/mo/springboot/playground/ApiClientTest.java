package uk.co.mo.springboot.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestClientTest(ApiClient.class)
@Import(RestConfiguration.class)
class ApiClientTest {
  @Autowired
  private ApiClient apiClient;
  @Autowired
  private MockRestServiceServer mockRestServiceServer;

  @Test
  void shouldReturnString() {
    var mockedValue = "a test";
    mockRestServiceServer
      .expect(MockRestRequestMatchers.requestTo("http://api.example.com"))
      .andRespond(MockRestResponseCreators.withSuccess(mockedValue, MediaType.TEXT_PLAIN));

    var value = apiClient.getValue();

    mockRestServiceServer.verify();
    assertEquals(mockedValue, value);
  }
}
