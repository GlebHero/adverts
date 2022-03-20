package com.gleb.zemskoi.adverts.config;

import com.gleb.zemskoi.adverts.entity.common.Error;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.exception.ForbiddenException;
import com.gleb.zemskoi.adverts.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errorList = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            Error error = new Error();
            error.setError("Object: " + fieldError.getObjectName() + ". Field: " + fieldError.getField());
            error.setErrorMessage(fieldError.getDefaultMessage());
            errorList.add(error);
        }
        RestResponseEntity<Object> restResponseEntity = new RestResponseEntity<>(errorList);
        return new ResponseEntity<>(restResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        RestResponseEntity<Object> restResponseEntity = new RestResponseEntity<>(Collections.singletonList(new Error(ex.getClass().getSimpleName(), ex.getMessage())));
        return new ResponseEntity<>(restResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex) {
        RestResponseEntity<Object> restResponseEntity = new RestResponseEntity<>(Collections.singletonList(new Error(ex.getClass().getSimpleName(), ex.getMessage())));
        return new ResponseEntity<>(restResponseEntity, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        RestResponseEntity<Object> restResponseEntity = new RestResponseEntity<>(Collections.singletonList(new Error(ex.getClass().getSimpleName(), "Entity already exist")));
        return new ResponseEntity<>(restResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleAnyException(Throwable ex) {
        RestResponseEntity<Object> restResponseEntity = new RestResponseEntity<>(Collections.singletonList(new Error(ex.getClass().getSimpleName(), ex.getMessage())));
        return new ResponseEntity<>(restResponseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
