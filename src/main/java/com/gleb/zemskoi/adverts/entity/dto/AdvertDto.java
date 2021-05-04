package com.gleb.zemskoi.adverts.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertDto {
    private UUID uuid;
    @NotNull
    private UUID customerUuid;
    @NotEmpty
    @Size(max = 100)
    private String title;
    @NotEmpty
    @Size(max = 1000)
    private String description;
    @NotNull
    @Digits(fraction = 2, integer = 10)
    @PositiveOrZero
    private BigDecimal price;
}
