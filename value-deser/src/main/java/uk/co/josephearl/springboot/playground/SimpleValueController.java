package uk.co.josephearl.springboot.playground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class SimpleValueController {
  @PutMapping("/simple")
  public void simple(@Valid @RequestBody SimpleValueRequest request) {
    log.info("Received request: " + request);
  }
}
