package com.gleb.zemskoi.adverts.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "customer")
public class Advert {
    @Id
    @NonNull
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @NotNull
    private Customer customer;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotNull
    private BigDecimal price;
    private LocalDateTime createDate;
}
