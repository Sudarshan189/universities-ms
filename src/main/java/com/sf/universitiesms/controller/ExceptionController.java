package com.sf.universitiesms.controller;

import com.sf.universitiesms.exception.AppException;
import com.sf.universitiesms.exception.NoUniversityFound;
import com.sf.universitiesms.exception.SeatFullException;
import com.sf.universitiesms.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(SeatFullException.class)
    public ResponseEntity<Error> catchSeatFullException(SeatFullException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoUniversityFound.class)
    public ResponseEntity<Error> catchNoUniversityFound(NoUniversityFound e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Error> catchAppException(AppException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
