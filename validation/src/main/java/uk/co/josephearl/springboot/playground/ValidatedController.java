package uk.co.josephearl.springboot.playground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@Slf4j
@Validated
@RestController
public class ValidatedController {
  @PutMapping("/validated-request-body")
  public void requestBody(@RequestBody ValidRequest request) {
    log.info("Received request: " + request);
  }

  @GetMapping("/validated-request-param")
  public void requestParam(@RequestParam("param") @Pattern(regexp = "^[0-9]+$") String param) {
    log.info("Received param: " + param);
  }
}
