package com.gleb.zemskoi.adverts.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @NonNull
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Advert> advertList;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotNull
    private LocalDate birthDate;
}
