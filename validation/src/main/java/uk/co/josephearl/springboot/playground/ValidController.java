package uk.co.josephearl.springboot.playground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Slf4j
@RestController
public class ValidController {
  @PutMapping("/valid-request-body")
  public void requestBody(@RequestBody @Valid ValidRequest request) {
    log.info("Received request: " + request);
  }

  @GetMapping("/valid-request-param")
  public void requestParam(@RequestParam("param") @Valid @Pattern(regexp = "^[0-9]+$") String param) {
    log.info("Received param: " + param);
  }
}
