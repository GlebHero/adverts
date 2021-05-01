package com.gleb.zemskoi.adverts.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    private String title;
    @NotEmpty
    private String description;
    @NotNull
    private BigDecimal price;
}
