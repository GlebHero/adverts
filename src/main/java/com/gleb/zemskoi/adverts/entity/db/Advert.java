package com.gleb.zemskoi.adverts.entity.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gleb.zemskoi.adverts.entity.enums.AdvertStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString(exclude = "customer")
public class Advert {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String phoneNumber;
    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime updateDate;
    @NotNull
    @Column(name = "advert_status")
    @Enumerated(EnumType.STRING)
    private AdvertStatusEnum advertStatusEnum;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "uuid", name = "customer_uuid", nullable = false, updatable = false)
    @JsonBackReference
    private Customer customer;
}
