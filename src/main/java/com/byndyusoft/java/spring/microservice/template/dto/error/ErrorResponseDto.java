package com.byndyusoft.java.spring.microservice.template.dto.error;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ErrorResponseDto {

  private final List<ErrorMessageDto> errors;

  public ErrorResponseDto(final List<ErrorMessageDto> errors) {
    this.errors = errors;
  }

  public ErrorResponseDto(final String message) {
    this(null, message);
  }

  public ErrorResponseDto(final String code, final String message) {
    this.errors = new ArrayList<>();
    errors.add(new ErrorMessageDto(code, message));
  }
}
