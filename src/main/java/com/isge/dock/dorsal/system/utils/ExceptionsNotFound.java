package com.isge.dock.dorsal.system.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionsNotFound {

    @ExceptionHandler(value = AfficherMessage.class)
    public ResponseEntity<Object> handleException(AfficherMessage ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}