package com.gleb.zemskoi.adverts.entity.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Advert {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private UUID uuid;
    @NotNull
    @Column(nullable = false)
    @Basic
    private UUID customerUuid;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private LocalDateTime createDate;
}
