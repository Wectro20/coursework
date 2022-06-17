package com.andrii.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ConferenceNotFoundExceptionHandler {
    @ExceptionHandler(ConferenceNotFoundException.class)
    public ResponseEntity<Object> handleTechniqueNotFoundException(ConferenceNotFoundException e) {
        ConferenceException techniqueException = new ConferenceException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(techniqueException, HttpStatus.NOT_FOUND);
    }
}
