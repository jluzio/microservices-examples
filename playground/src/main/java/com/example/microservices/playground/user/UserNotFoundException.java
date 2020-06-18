package com.example.microservices.playground.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.microservices.playground.exception.ApiException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ApiException {

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserNotFoundException(String message) {
    super(message);
  }

}
