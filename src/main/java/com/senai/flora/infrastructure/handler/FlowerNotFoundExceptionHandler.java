package com.senai.flora.infrastructure.handler;

import com.senai.flora.domain.exception.FlowerNotFoundException;
import com.senai.flora.domain.exception.InvalidArgumentException;
import com.senai.flora.domain.exception.InvalidPriceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class FlowerNotFoundExceptionHandler {
    @ExceptionHandler(FlowerNotFoundException.class)
    public ResponseEntity<?> handleFlowerNotFoundException(FlowerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }
}
