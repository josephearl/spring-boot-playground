package uk.co.mo.springboot.playground;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {
  private final RestTemplate restTemplate;

  public ApiClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public StringEnum getValue() {
    return restTemplate.getForObject("/string", StringEnum.class);
  }
}
