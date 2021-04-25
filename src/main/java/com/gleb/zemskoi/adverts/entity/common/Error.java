package com.gleb.zemskoi.adverts.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
    private String error;
    private String errorMessage;
}
