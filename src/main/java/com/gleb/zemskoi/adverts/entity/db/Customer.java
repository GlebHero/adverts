package com.gleb.zemskoi.adverts.entity.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gleb.zemskoi.adverts.entity.enums.CustomerStatusEnum;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "password")
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;
    @NotBlank
    private String name;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private LocalDate birthDate;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime updateDate;
    @NotNull
    @Column(name = "customer_status")
    @Enumerated(EnumType.STRING)
    private CustomerStatusEnum customerStatusEnum;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    @JsonManagedReference
    private List<Advert> adverts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
