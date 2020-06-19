package com.example.microservices.playground.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class AppResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({AppException.class})
  public ResponseEntity<Object> handleAppException(Exception ex, WebRequest request) throws Exception {
    var headers = new HttpHeaders();
    var status = getExceptionHttpStatus(ex);
    return handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    var errorBody = ExceptionResponse.builder()
        .message("Validation Failed")
        .detail(errors.toString())
        .status(status)
        .timestamp(LocalDateTime.now())
        .build();
    return handleExceptionInternal(ex, errorBody, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request
  ) {
    var errorBody = body;
    if (errorBody == null) {
      errorBody = createErrorResponse(ex, status);
    }
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
