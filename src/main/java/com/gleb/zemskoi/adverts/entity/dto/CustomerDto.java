package com.gleb.zemskoi.adverts.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
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
    @Email
    private String email;
    @NotBlank
    @Size(min = 10, max = 10)
    @Digits(fraction = 0, integer = 10)
    private String phoneNumber;
    @NotNull
    private LocalDate birthDate;
}
