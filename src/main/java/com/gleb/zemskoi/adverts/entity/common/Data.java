package com.gleb.zemskoi.adverts.entity.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
public class Data<T> {
    private T result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pagination pagination;

    public Data(T result, Pagination pagination) {
        this.result = result;
        this.pagination = pagination;
    }

    public Data(T result) {
        this.result = result;
    }
}
