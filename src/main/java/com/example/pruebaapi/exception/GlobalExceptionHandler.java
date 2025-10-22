package com.example.pruebaapi.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.spi.ToolProvider.findFirst;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> manejarResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> manejarValidacion(MethodArgumentNotValidException ex) {
        String mensajeError = "Error validacion";

        if (ex.getBindingResult().hasErrors()) {
            ObjectError Error = ex.getBindingResult().getAllErrors().get(0);

            if (Error instanceof FieldError) {
                FieldError fieldError = (FieldError) Error;
                mensajeError = fieldError.getField() + ": " + Error.getDefaultMessage();
            } else  {
                mensajeError = Error.getDefaultMessage();
            }
        }
        return new ResponseEntity<>(mensajeError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> manejarDatosDuplicados(DataIntegrityViolationException ex) {
        return new ResponseEntity<>("Error: El email ya está en uso.", HttpStatus.CONFLICT);
    }
}
