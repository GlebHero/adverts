package com.gleb.zemskoi.adverts.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private UUID uuid;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotNull
    private LocalDate birthDate;
}
