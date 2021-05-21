package com.gleb.zemskoi.adverts.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    @NotNull
    @Min(1)
    private Long page;

    @NotNull
    @Min(1)
    @Max(100)
    private Long size;
}
