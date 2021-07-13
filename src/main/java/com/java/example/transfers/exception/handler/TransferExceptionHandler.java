package com.java.example.transfers.exception.handler;

import com.java.example.transfers.exception.InvalidAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class TransferExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,List<String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            //String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        Map<String,List<String>> errorBody= new HashMap<>();
        errorBody.put("errors",errors);
        return new ResponseEntity<>(errorBody,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidAmountException.class,IllegalArgumentException.class})
    public ResponseEntity<Map<String,List<String>>> handleInvalidAmountException(
            RuntimeException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        Map<String,List<String>> errorBody= new HashMap<>();
        errorBody.put("errors",errors);
        return new ResponseEntity<>(errorBody,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,List<String>>> handleException(
            InvalidAmountException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        Map<String,List<String>> errorBody= new HashMap<>();
        errorBody.put("errors",errors);
        return new ResponseEntity<>(errorBody,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
