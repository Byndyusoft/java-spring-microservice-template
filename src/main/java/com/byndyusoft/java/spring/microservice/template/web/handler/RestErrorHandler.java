package com.byndyusoft.java.spring.microservice.template.web.handler;

import com.byndyusoft.java.spring.microservice.template.dto.error.ErrorMessageDto;
import com.byndyusoft.java.spring.microservice.template.dto.error.ErrorResponseDto;
import com.byndyusoft.java.spring.microservice.template.exception.BaseException;
import com.byndyusoft.java.spring.microservice.template.exception.ErrorCode;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class RestErrorHandler {

  private static final String LOG_PREFIX_WARN = "Warn: ";
  private static final String LOG_PREFIX_ERROR = "Error: ";

  private final Pattern messagePlaceholderPattern = Pattern.compile("\\{\\d+?}\\s?");
  private final MessageSource messageSource;

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ErrorResponseDto> handleBaseException(final BaseException ex) {
    final ErrorCode errorCode = ex.getErrorCode();
    final Object[] args = ex.getArgs();

    final ResponseEntity<ErrorResponseDto> responseEntity = ResponseEntity.status(errorCode.getHttpStatus())
        .body(args == null ? buildErrorResponse(errorCode) : buildErrorResponse(errorCode, args));
    log.warn(LOG_PREFIX_WARN + responseEntity.getBody(), ex);
    return responseEntity;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleException(final Exception ex) {
    log.error(LOG_PREFIX_ERROR, ex);
    return buildErrorResponse(ErrorCode.UNHANDLED, ex.getMessage());
  }

  private String buildMessage(final String messageKey, final Object... args) {
    final String originalMessage = messageSource.getMessage(messageKey, args, Locale.getDefault());
    return messagePlaceholderPattern.matcher(originalMessage).replaceAll("");
  }

  private ErrorResponseDto buildErrorResponse(final ErrorCode errorCode, final Object... args) {
    return new ErrorResponseDto(List.of(
        new ErrorMessageDto(errorCode.name(), buildMessage(errorCode.name(), args))
    ));
  }
}
