package org.testwork.gp_hotels.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
            log.warn("Validation error - Field: '{}', Message: '{}'",
                    error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleParseError(HttpMessageNotReadableException e) {
        log.warn("Parse error - Message: '{}'", e.getMessage());
        return ResponseEntity.badRequest().body("Invalid request format: " + e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDbConstraint(DataIntegrityViolationException e) {
        log.error("Database constraint violation - Message: '{}'", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Database error: " + e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(NoSuchElementException e) {
        log.warn("No such element - Message: '{}'", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Element not found: " + e.getMessage());
    }
}
