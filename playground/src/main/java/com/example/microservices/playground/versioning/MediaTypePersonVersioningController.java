package com.example.microservices.playground.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo/versioning/media-type")
public class MediaTypePersonVersioningController {

  @GetMapping(value = "person", produces = "application/vnd.company.app-v1+json")
  public PersonV1 getPersonV1() {
    return new PersonV1("John Doe");
  }

  @GetMapping(value = "person", produces = "application/vnd.company.app-v2+json")
  public PersonV2 getPersonV2() {
    return new PersonV2(new Name("John", "Doe"));
  }
}
