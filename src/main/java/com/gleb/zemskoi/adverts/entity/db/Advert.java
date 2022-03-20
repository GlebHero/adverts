package com.gleb.zemskoi.adverts.entity.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gleb.zemskoi.adverts.entity.enums.AdvertStatusEnum;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = "customer")
public class Advert implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Advert advert = (Advert) o;
        return id != null && Objects.equals(id, advert.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
