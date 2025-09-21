package steveduong.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import steveduong.dto.error.UnknownError;
import steveduong.dto.error.UserNotFoundError;
import steveduong.exception.UserNotFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<UserNotFoundError> handleUserNotFound(UserNotFoundException ex) {
    return new ResponseEntity<>(
        UserNotFoundError.builder()
            .error(ex.getMessage())
            .status(HttpStatus.NOT_FOUND.value())
            .build(),
        HttpStatus.NOT_FOUND);
  }

  // Catch-all for other unhandled exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<UnknownError> handleGenericException(Exception ex) {
    return new ResponseEntity<>(
        UnknownError.builder()
            .error("An unexpected error occurred.")
            .rootCause(ex.getMessage())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
