package com.gleb.zemskoi.adverts.entity.common;

import lombok.AllArgsConstructor;

@lombok.Data
@AllArgsConstructor
public class Data<T> {
    private T result;
}
