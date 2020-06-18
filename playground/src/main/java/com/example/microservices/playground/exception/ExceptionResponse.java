package com.example.microservices.playground.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionResponse {
    private String message;
    private String detail;
    private LocalDateTime timestamp;
    private HttpStatus status;

}
