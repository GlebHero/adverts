package com.gleb.zemskoi.adverts.entity.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data<T> {
    private T result;
}
