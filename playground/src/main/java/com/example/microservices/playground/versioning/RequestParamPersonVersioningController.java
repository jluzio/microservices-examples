package com.example.microservices.playground.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo/versioning/param")
public class RequestParamPersonVersioningController {

  @GetMapping(value = "person", params = "version=1")
  public PersonV1 getPersonV1() {
    return new PersonV1("John Doe");
  }

  @GetMapping(value = "person", params = "version=2")
  public PersonV2 getPersonV2() {
    return new PersonV2(new Name("John", "Doe"));
  }
}
