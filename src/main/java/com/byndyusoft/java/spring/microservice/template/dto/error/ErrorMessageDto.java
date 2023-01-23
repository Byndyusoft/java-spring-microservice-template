package com.byndyusoft.java.spring.microservice.template.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ErrorMessageDto {
  private String code;
  private String message;
}
