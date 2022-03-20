package com.gleb.zemskoi.adverts.entity.common;


import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class RestResponseEntity<T> {
    private Data<T> data;
    private List<Error> errorList = new ArrayList<>();

    public RestResponseEntity(Data<T> data, List<Error> errorList) {
        this.data = data;
        this.errorList = errorList;
    }

    public RestResponseEntity(List<Error> errorList) {
        this.errorList = errorList;
    }

    public RestResponseEntity(T t) {
        this.data = new Data<>(t);
    }

    public RestResponseEntity(Data<T> data) {
        this.data = data;
    }
}
