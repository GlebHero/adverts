package com.gleb.zemskoi.adverts.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String field, String value) {
        super("'" + field + "'" + " " + "'" + value + "'" + ", not found in db");
    }
}
