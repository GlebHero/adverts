package com.gleb.zemskoi.adverts.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private UUID uuid;
    @NotBlank
    @Size(max = 100)
    private String username;
    @NotBlank
    @Size(max = 100)
    private String password;
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    @Size(max = 100)
    private String lastName;
    @NotBlank
    @Email
    @Size(max = 100)
    private String email;
    @NotBlank
    @Size(min = 10, max = 10)
    @Digits(fraction = 0, integer = 10)
    private String phoneNumber;
    @NotNull
    private LocalDate birthDate;
}
