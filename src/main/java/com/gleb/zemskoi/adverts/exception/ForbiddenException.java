package com.gleb.zemskoi.adverts.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("Forbidden operation!");
    }
}
