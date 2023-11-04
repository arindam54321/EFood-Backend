package com.ari.efood.utils;

import com.ari.efood.exception.CustomerException;
import com.ari.efood.exception.CustomerOtpException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandlers {
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ResponseWrapper<String>> handleCustomerException(CustomerException e) {
        String message = e.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseWrapper.entity(message, status);
    }

    @ExceptionHandler(CustomerOtpException.class)
    public ResponseEntity<ResponseWrapper<String>> handleCustomerOtpException(CustomerOtpException e) {
        String message = e.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseWrapper.entity(message, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> data = e.getFieldErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.toList());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseWrapper.entity(data, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseWrapper<List<String>>> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> data = e.getConstraintViolations()
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .toList();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseWrapper.entity(data, status);
    }
}
