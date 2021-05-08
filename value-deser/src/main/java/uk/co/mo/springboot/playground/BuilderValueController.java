package uk.co.mo.springboot.playground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class BuilderValueController {
  @PutMapping("/builder")
  public void builder(@Valid @RequestBody BuilderValueRequest request) {
    log.info("Received request: " + request);
  }
}
