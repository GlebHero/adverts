package com.gleb.zemskoi.adverts.bean;

import com.gleb.zemskoi.adverts.entity.common.Error;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;
import java.util.Collections;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Clock clock;

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDateTime.now(clock));
//        body.put("status", HttpStatus.BAD_REQUEST.value());
//        List<String> errors = new ArrayList<>();
//        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
//            errors.add(fieldError.getObjectName() + "." + fieldError.getField() + " " + fieldError.getDefaultMessage());
//        }
//        body.put("errors", errors);
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        RestResponseEntity<Object> restResponseEntity = new RestResponseEntity<>(Collections.singletonList(new Error(ex.getClass().getName(), ex.getMessage())));
        return new ResponseEntity<>(restResponseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<Object> handleBadRequestException(HttpClientErrorException.BadRequest ex) {
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
