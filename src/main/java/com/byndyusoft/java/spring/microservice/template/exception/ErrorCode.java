package com.byndyusoft.java.spring.microservice.template.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND),
  DUPLICATE_ADDRESS(HttpStatus.CONFLICT),

  UNHANDLED(HttpStatus.INTERNAL_SERVER_ERROR);

  private final HttpStatus httpStatus;
}
