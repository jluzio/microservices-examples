package com.example.microservices.playground.config;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ActuatorConfig {
  
  // Added the following actuators that weren't activated by default
  
  @Profile("actuators-extended")
  @Bean
  public AuditEventRepository auditEventRepository() {
    return new InMemoryAuditEventRepository();
  }
  
  @Profile("actuators-extended")
  @Bean
  public HttpTraceRepository httpTraceRepository() {
    return new InMemoryHttpTraceRepository();
  }

}
