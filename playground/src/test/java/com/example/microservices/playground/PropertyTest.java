package com.example.microservices.playground;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
class PropertyTest {
  @Value("${spring.messages.basename:null}")
  private String appProp;
  @Value("${springdoc.swagger-ui.enabled:null}")
  private String testProp;

  @Test
  void test() {
    log.info("props: {} | {}", appProp, testProp);
  }

}
