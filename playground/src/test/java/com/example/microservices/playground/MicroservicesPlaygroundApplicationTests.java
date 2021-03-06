package com.example.microservices.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MicroservicesPlaygroundApplicationTests {
  @Value("${spring.messages.basename}")
  private String appProp;
  @Value("${springdoc.swagger-ui.enabled}")
  private String testProp;

  @Test
  void contextLoads() {
    log.info("props: {} | {}", appProp, testProp);
  }

}
