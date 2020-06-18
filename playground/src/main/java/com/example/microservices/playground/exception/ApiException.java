package com.example.microservices.playground.exception;

public class ApiException extends AppException {

  public ApiException() {
    super();
  }

  public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApiException(String message) {
    super(message);
  }

  public ApiException(Throwable cause) {
    super(cause);
  }

}
