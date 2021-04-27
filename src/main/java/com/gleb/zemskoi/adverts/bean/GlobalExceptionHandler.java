package com.gleb.zemskoi.adverts.bean;

import com.gleb.zemskoi.adverts.entity.common.Error;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Clock clock;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        RestResponseEntity<Object> restResponseEntity = new RestResponseEntity<>(Collections.singletonList(new Error(ex.getClass().getName(), ex.getMessage())));
        return new ResponseEntity<>(restResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errorList = new ArrayList<>();
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {
            Error error = new Error();
            error.setError("Object: " + fieldError.getObjectName() + ". Field: " + fieldError.getField());
            error.setErrorMessage(fieldError.getDefaultMessage());
            errorList.add(error);
        }
        RestResponseEntity<Object> restResponseEntity = new RestResponseEntity<>(errorList);
        return new ResponseEntity<>(restResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleAnyException(Throwable ex) {
        RestResponseEntity<Object> restResponseEntity = new RestResponseEntity<>(Collections.singletonList(new Error(ex.getClass().getName(), ex.getMessage())));
        return new ResponseEntity<>(restResponseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<Object> handleNotFoundException(SQLException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDateTime.now(clock));
//        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        body.put("error", ex.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
