package com.example.microservices.playground.filtered;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

//@Data
//@Builder
public class UserAccount {
  public static interface Views {
    interface Simple {};
    interface Detailed extends Simple {};
  }

  @JsonView(Views.Simple.class)
  private String id;
  @JsonView(Views.Detailed.class)
  private String name;
  @JsonView(Views.Detailed.class)
  private String email;
  @Schema(hidden = true)
  private String password;
  @JsonIgnore
  private LocalDateTime internalField = LocalDateTime.now();


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDateTime getInternalField() {
    return internalField;
  }

  public void setInternalField(LocalDateTime internalField) {
    this.internalField = internalField;
  }
}
