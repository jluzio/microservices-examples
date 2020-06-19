package com.example.microservices.playground.user;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
  private String street;
  private String city;
  private String country;
}
