package com.byndyusoft.java.spring.microservice.template.exception;

import java.util.Arrays;
import lombok.ToString;

@ToString
public class BaseException extends RuntimeException {

  private final ErrorCode errorCode;
  private final String[] args;

  public BaseException(final ErrorCode errorCode, final Object... args) {
    this.errorCode = errorCode;
    this.args = Arrays.stream(args).map(String::valueOf).toArray(String[]::new);
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public String[] getArgs() {
    return args == null ? null : Arrays.copyOf(args, args.length);
  }
}
