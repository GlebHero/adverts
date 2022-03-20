package com.gleb.zemskoi.adverts.entity.db;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = "data")
public class Attachment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;
    @Column
    private String fileName;
    @Column
    private String fileType;
    @Column(columnDefinition = "bytea")
    @Type(type="org.hibernate.type.BinaryType")
    @Lob
    private byte[] data;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "uuid", name = "advert_uuid", nullable = false, updatable = false)
    private Advert advert;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Attachment that = (Attachment) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
