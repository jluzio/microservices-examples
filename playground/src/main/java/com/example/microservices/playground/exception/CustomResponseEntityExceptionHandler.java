package com.example.microservices.playground.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({AppException.class})
  public ResponseEntity<Object> handleAppException(Exception ex, WebRequest request) throws Exception {
    var headers = new HttpHeaders();
    var status = getExceptionHttpStatus(ex);
    return handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request
  ) {
    var errorBody = createErrorResponse(ex, status);
    return super.handleExceptionInternal(ex, errorBody, headers, status, request);
  }

  private ExceptionResponse createErrorResponse(Exception ex, HttpStatus status) {
    return ExceptionResponse.builder()
        .message(ex.getMessage())
        .detail(ex.getLocalizedMessage())
        .timestamp(LocalDateTime.now())
        .status(status)
        .build();
  }

  private HttpStatus getExceptionHttpStatus(Exception ex) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
    if (responseStatus != null) {
      httpStatus = responseStatus.code() != null ? responseStatus.code() : responseStatus.value();
    }
    return httpStatus;
  }

}
