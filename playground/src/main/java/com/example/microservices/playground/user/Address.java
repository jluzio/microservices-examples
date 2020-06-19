package com.example.microservices.playground.user;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Data
@Embeddable
public class Address {
  @NotEmpty
  private String street;
  @NotEmpty
  private String city;
  @NotEmpty
  private String country;
}
