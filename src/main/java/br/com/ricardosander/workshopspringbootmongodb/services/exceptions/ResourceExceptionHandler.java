package br.com.ricardosander.workshopspringbootmongodb.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception,
      HttpServletRequest request) {


    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError standardError =
        new StandardError(System.currentTimeMillis(), status.value(), "Not found",
            exception.getMessage(), request.getRequestURI());

    return ResponseEntity.status(status).body(standardError);
  }

}
