package uk.co.josephearl.springboot.playground;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VanillaWebFluxApplicationTest {
  private Thread applicationThread = new Thread(() -> VanillaWebFluxApplication.main(new String[0]));
  private TestRestTemplate testRestTemplate = new TestRestTemplate();

  @BeforeEach
  void start() throws InterruptedException {
    applicationThread.setDaemon(true);
    applicationThread.start();
    // Wait until the HTTP server is running in a busy loop
    while (true) {
      try {
        testRestTemplate.getForEntity("http://localhost:8888/people/1", String.class);
        break;
      } catch (ResourceAccessException ignored) {
      }
      Thread.sleep(10L);
    }
  }

  @AfterEach
  void stop() throws InterruptedException {
    applicationThread.interrupt();
    applicationThread.join();
  }

  @Test
  void shouldReturnOkWithId() {
    var response
      = testRestTemplate.postForEntity("http://localhost:8888/people", null, String.class);

    assertAll("response",
      () -> assertEquals(200, response.getStatusCodeValue()),
      () -> assertEquals("1", response.getBody()));
  }
}
